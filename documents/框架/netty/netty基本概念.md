[TOC]

#### 基本名词

##### **`Channel`**（相当于`Socket`）

传入或者传出数据的**载体**，代表一个到实体（如一个硬件设备、一个文件、一个网络套接字或者一个能够执行一个或者多个不同的I/O操作的程序组件）的开发连接，如读操作或者写操作。可以被打开或者被关闭，连接或者断开连接。

可以给用户提供：

1. `channel`当前的状态
2. `channel`配置的参数
3. `channel`所支持的I/O操作
4. 与该`channel`关联的可以处理所有I/O事件和请求的`ChannelPipeline`

基本的I/O操作`bind()`、`connect()`、`read()`和`write()`等依赖于底层网络传输所提供的原语。Netty中`Channel`接口所提供的Api，大大降低了直接使用`Socket`类的复杂性。

Netty中所有的I/O操作都是异步的。这就意味着任何I/O调用都将立即返回，但是并不能保证调用结束后请求的I/O操作已经完成。因此，将返回一个ChannelFuture实例，该实例在请求的I/O操作成功、失败或者取消后进行通知。

+ `EmbeddedChannel`
+ `LocalServerChannel`
+ `NioDatagramChannel`
+ `NioSctpChannel`
+ `NioSocketChannel`

`Channel`是有层次的，具体取决于它是如何创建出来的。比如，`ServerSocketChannel`的parent是`SocketChannel`。层次结构的语义取决于通道所属的传输的实现。

**`Channel`重要的方法：**

| 方法名          | 描述                                                         |
| --------------- | ------------------------------------------------------------ |
| `eventLoop`     | 返回分配给`Channel`的`EventLoop`                             |
| `pipeline`      | 返回分配给`Channel`的`ChannelPipeline`                       |
| `isActive`      | 如果`Channel` 是活动的，则返回true。活动的意义可能依赖于底层的传输。例如，一个Socket 传输一旦连接到了远程节点便是活动的，而一个Datagram 传输一旦被打开便是活动的 |
| `localAddress`  | 返回本地的`SokcetAddress`                                    |
| `remoteAddress` | 返回远程的`SocketAddress`                                    |
| `write`         | 将数据写到远程节点。这个数据将被传递给`ChannelPipeline`，并且排队直到它被冲刷 |
| `flush`         | 将之前已写的数据冲刷到底层传输，如一个Socket                 |
| `writeAndFlush` | 一个简便的方法，等同于调用`write()`并接着调用`flush()`       |

**`Channel`的生命周期：**

| 状态                  | 描述                                                         |
| --------------------- | ------------------------------------------------------------ |
| `ChannelUnregistered` | `Channel`已经被创建，但还未注册到`EventLoop`                 |
| `ChannelRegistered`   | `Channel`已经被注册到`EventLoop`                             |
| `ChannelActive`       | `Channel`处于活动状态（已经连接到它的远程节点）。可以接收和发送数据 |
| `ChannelInactive`     | `Channel`没有连接到远程节点                                  |

![Channel的状态模型](../../image/netty/Channel的状态模型.png)

**`Channel`的状态发生改变时，将会生成对应的事件，这些事件将会被转发给`ChannelPipeline`中的`ChannelHandler`，其可以随后对它们做出响应。**



##### **`ChannelFuture`**（异步通知，将会被执行的操作）

在操作完时通知应用程序的一种方式。`ChannelFuture`提供了几种额外的方法，这些方法（如：`addListener()`方法）使得我们能够注册一个或者多个`ChannelFutureListener`实例。监听器的回调方法`operationComplete()`，将会在对应的操作完成时被调用，然后监听器可以判断该操作是成功完成或者出错了。



##### `EventLoopGroup`

特殊的`EventExecutorGroup`，它允许注册通道，以便在事件循环期间进行后续的选择。

![Netty中EventLoop类继承图](../../image/netty/Netty中EventLoop类继承图.png)

##### **`EventLoop`**（控制流、多线程处理、并发）

一旦一个`Channel`注册到`EventLoop`，则将处理`Channel`中所有的I/O操作。一个`EventLoop`实例通常会处理多个`Channel`。

每个`Channel`分配一个`EventLoop`（**一个给定`Channel`**的**I/O操作**都是由**相同的`Thread`**执行，实际上消除了对于同步的需要），用于处理连接的生命周期中所有的事件，包括：

1. 注册感兴趣的事件；
2. 将事件派发给`ChannelHandler`;
3. 安排进一步的动作

`Channel`、`EventLoop`和`EventLoopGroup`之间的关系：

![Channel、EventLoop、Thread 以及EventLoopGroup 之间的关系](../../image/netty/Channel、EventLoop、Thread 以及EventLoopGroup 之间的关系.png)

1. 一个`EventLoopGroup`包含一个或者多个`EventLoop`
2. **一个`EventLoop`在它的生命周期内只和一个`Thread`绑定**
3. **所有由`EventLoop`处理的I/O事件都将在它专有的`Thread`上被处理**
4. **一个`Channel`在它的生命周期内只注册于一个`EventLoop`**
5. **一个`EventLoop`可能会被分配给一个或者多个`Channel`**

##### **`ChannelHandler`**（处理入站和出站数据的应用程序逻辑的容器）

当一个回调被触发时（操作完通知应用程序），相关的**事件**会被一个`ChannelHandler`接口的实现处理。

1. 应用程序通过实现或者扩展`ChannelHandler`来挂钩到事件的生命周期，并且提供自定义的应用程序逻辑
2. 在架构上，`ChannelHandler`有助于保持业务逻辑与网络处理代码的分离

`ChannelInboundHandler、ChannelOutboundHandler`是以自身角度出发来看数据的流动方向的。例如，从客户端应用程序来看，从客户端到服务端就是出站，使用`ChannelOutboundHandler`，反之，从服务端到客户端就是入站，使用`ChannelInboundHandler`。

**`ChannelHandler`典型用途：**

1. 将数据从一种格式转换为另一种格式
2. 提供异常的通知
3. 提供`Channel`变为活动或者非活动的通知
4. 提供当`Channel`注册到`EventLoop`或者`EventLoop`注销时的通知
5. 提供有关用户自定义事件的通知

**`ChannelHandler`的生命周期方法：**

| 类型              | 描述                                                  |
| ----------------- | ----------------------------------------------------- |
| `handlerAdded`    | 当把`ChannelHandler`添加到`ChannelPipeline`中时被调用 |
| `handlerRemoved`  | 当从ChannelPipeline中移除`ChannelHandler`时被调用     |
| `exceptionCaught` | 当处理过程中在`ChannelPipeline`中有错误产生时被调用   |

**`ChannelInboundHandler`接口**

`ChannelInboundHandler`的方法

| 类型                        | 描述                                                         |
| --------------------------- | ------------------------------------------------------------ |
| `channelRegistered`         | 当`Channel`已经注册到它的`EventLoop`并且能够处理I/O时被调用  |
| `channelUnregistered`       | 当`Channel`从它的`EventLoop`注销并且无法处理任何I/O时被调用  |
| `channelActive`             | 当`Channel`处于活动状态时被调用；`Channel`已经连接/绑定并且已经就绪 |
| `channelInactive`           | 当`Channel`离开活动状态并且不再连接它的远程节点时被调用      |
| `channelReadComplete`       | 当`Channel`上的一个读操作完成时被调用                        |
| `channelRead`               | 当从`Channel`读取数据时被调用                                |
| `ChannelWritabilityChanged` | 当`Channel`的可写状态发生改变时被调用。<br/>用户可以确保写操作不会完成得太快（以避免发生`OutOfMemoryError`）或者可以在`Channel`变为再次可写时恢复写入。可以通过调用`Channel`的`isWritable()`方法来检测Channel的可写性。与可写性相关的阈值可以通过`Channel.config().setWriteHighWaterMark()`和`Chnnel.config().setWriteLowWaterMark()`方法来设置 |
| `userEventTriggered`        | 当`ChannelInboundHandler.fireUserEventTriggered()`方法被调用时被调用，因为一个POJO从`ChannelPipeline`中传经 |

**`ChannelOutboundHandler`接口**

**`ChannelOutboundHandler`的方法：**

| 类型                                                         | 描述                                                |
| ------------------------------------------------------------ | --------------------------------------------------- |
| `bind(ChannelHandlerContext,SocketAddress,ChannelPromise)`   | 当请求将`Channel`绑定到本地地址时被调用             |
| `connect(ChannelHandlerContext,SocketAddress,SocketAddress,ChannelPromise)` | 当请求将`Channel`连接到远程节点时被调用             |
| `disconnect(ChannelHandlerContext,ChannelPromise)`           | 当请求将`Channel`从远程节点断开时被调用             |
| `close(ChannelHandlerContext,ChannelPromise)`                | 当请求关闭`Channel`时被调用                         |
| `deregister(ChannelHandlerContext,ChannelPromise)`           | 当请求将`Channel`从它的`EventLoop`注销时被调用      |
| `read(ChannelHandlerContext)`                                | 当请求从`Channel`读取更多的数据时被调用             |
| `flush(ChannelHandlerContext)`                               | 当请求通过`Channel`将入队数据冲刷到远程节点时被调用 |
| `write(ChannelHandlerContext,Object,ChannelPromise)`         | 当请求通过`Channel`将数据写入远程节点时被调用       |

**`ChannelHnadlerContext`接口**

`ChannelHandlerContext`代表了`ChannelHandler`和`ChannelPipeline`之间的关联。**每当有`ChannelHandler`添加到`ChannelPipeline`中时，都会创建`ChannelHandlerContext`。**

`ChannelHandlerContext`的主要功能是管理它所关联的`ChannelHandler`和在同一个`ChannelPipeline`中的其他`ChannelHandler`之间的交互。

**`ChannelHandlerContext`的API：**

| 方法名                          | 描述                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| `alloc`                         | 返回和这个实例相关联的`Channel`所配置的`ByteBufAllocator`    |
| `bind`                          | 绑定到给定的`SocketAddress`，并返回`ChannelFuture`           |
| `channel`                       | 返回绑定到这个实例的`Channel`                                |
| `close`                         | 关闭`Channel`，并返回`ChannelFuture`                         |
| `connect`                       | 连接给定的`SocketAddress`，并返回`ChannelFuture`             |
| `deregister`                    | 从之前分配的`EventExecutor`注销，并返回`ChannelFuture`       |
| `executor`                      | 返回调度事件的`EventExecutor`                                |
| `fireChannelActive`             | 触发对下一个`ChannelInboundHandler`上的`channelActive()`方法（已连接）的调用 |
| `fireChannelInactive`           | 触发对下一个`ChannelInboundHandler`上的`channelInactive()`方法（已关闭）的调用 |
| `fireChannelRead`               | 触发对下一个`ChannelInboundHandler`上的`channelRead()`方法（已接收的消息）的调用 |
| `fireChannelReadComplete`       | 触发对下一个`ChannelInboundHandler`上的`channelReadComplete()`方法的调用 |
| `fireChannelRegistered`         | 触发对下一个`ChannelInboundHandler`上的`fireChannelRegistered()`方法的调用 |
| `fireChannelUnregistered`       | 触发对下一个`ChannelInboundHandler`上的`fireChannelUnregistered()`方法的调用 |
| `fireChannelWritabilityChanged` | 触发对下一个`ChannelInboundHandler`上的`fireChannelWritabilityChanged()`方法的调用 |
| `fireExceptionCaught`           | 触发对下一个`ChannelInboundHandler`上的`fireExceptionCaught(Throwable)`方法的调用 |
| `fireUserEventTriggered`        | 触发对下一个`ChannelInboundHandler`上的`fireUserEventTriggered(Object evt)`方法的调用 |
| `handler`                       | 返回绑定到这个实例的`ChannelHandler`                         |
| `isRemoved`                     | 如果所关联的`ChannelHandler`已经被从`ChannelPipeline`中移除则返回true |
| `name`                          | 返回这个实例的唯一名称                                       |
| `pipeline`                      | 返回这个实例所关联的`ChannelPipeline`                        |
| `read`                          | 将数据从`Channel`读取到第一个入站缓冲区；如果读取成功则触发一个`channelRead`事件，并（在最后一个消息被读取完成后）通知`ChannelInboundHandler`的`channelReadComplete(ChannelHandlerContext)`方法 |
| `write`                         | 通过这个实例写入消息并经过`ChannelPipeline`                  |
| `writeAndFlush`                 | 通过这个实例写入并冲刷消息并经过`ChannelPipeline`            |



##### **`ChannelPromise`**

`ChannelPromise`是`ChannelFuture`的一个子类，其定义了一个可写的方法，如`setSuccess()`和`setFailure()`，从而使`ChannelFuture`不可变。

##### **`ChannelPipeline`**（`ChannelHandler`链的容器）

每个`Channel`都拥有一个与之关联的`ChannelPipeline`，其持有一个`ChannelHandler`的实例链。**当`Channel`被创建后，会自动的分配到它专属的`ChannelPipeline`。**

提供了`ChannelHandler`链的容器，并定义了用于在该链上传播入站和出站事件流的API。

1. `ChannelPipeline`保存了与`Channel`相关联的`ChannelHandler`
2. `ChannelPipeline`可以根据需要，通过添加或者删除`ChannelHandler`来动态的修改
3. `ChannelPipeline`有着丰富的API用以被调用，以响应入站和出站事件

`ChannelHandler`安装到`ChannelPipeline`中的过程如下：

```java
// 添加一个EchoServerHandler到子Channel的ChannelPipeline
serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
    // 当一个新的连接被接受时，一个新的子Channel将会被创建，而ChannelInitializer将会把EchoServerHandler的实例添加到该Channel的ChannelPipeline中
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(serverHandler);
    }
});
```

1. 一个`ChannelInitializer`的实现被注册到了`ServerBootstrap`中
2. 当`ChannelInitializer.initChannel()`方法被调用时，`ChannelInitializer`将在`ChannelPipeline`中安装一组自定义的`ChannelHandler`
3. `ChannelInitializer`将它自己从`ChannelPipeline`中移除

**`ChannelPipeline`的入站操作：**

| 方法名称                        | 描述                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| `fireChannelRegistered`         | 调用`ChannelPipeline`中下一个`ChannelInboundHandler`的`channelRegistered（ChannelHandlerContext`）方法 |
| `fireChannelUnregisted`         | 调用`ChannelPipeline`中下一个`ChannelInboundHandler`的`channelUnregistered(ChannelHandlerContext)`方法 |
| `fireChannelActive`             | 调用`ChannelPipeline`中下一个`ChannelInboundHandler`的`channelActive(ChannelHandlerContext)`方法 |
| `fireChannelInactive`           | 调用`ChannelPipeline`中下一个`ChannelInboundHandler`的`channelInactive(ChannelHandlerContext)`方法 |
| `fireExceptionCaught`           | 调用`ChannelPipeline`中下一个`ChannelInboundHandler`的`exceptionCaught(ChannelHandlerContext, Throwable)`方法 |
| `fireUserEventTriggered`        | 调用`ChannelPipeline`中下一个`ChannelInboundHandler`的`userEventTriggered(ChannelHandlerContext, Object)`方法 |
| `fireChannelRead`               | 调用`ChannelPipeline` 中下一个`ChannelInboundHandler` 的`channelRead(ChannelHandlerContext, Object msg)`方法 |
| `fireChannelReadComplete`       | 调用`ChannelPipeline`中下一个`ChannelInboundHandler`的`channelReadComplete(ChannelHandlerContext)`方法 |
| `fireChannelWritabilityChanged` | 调用`ChannelPipeline`中下一个`ChannelInboundHandler`的`channelWritabilityChanged(ChannelHandlerContext)`方法 |

**`ChannelPipeline`的出站操作：**

| 方法名称        | 描述                                                         |
| --------------- | ------------------------------------------------------------ |
| `bind`          | 将`Channel`绑定到一个本地地址，这将调用`ChannelPipeline`中的下一个`ChannelOutboundHandler`的`bind(ChannelHandlerContext, SocketAddress, ChannelPromise)`方法 |
| `connect`       | 将`Channel`连接到一个远程地址，这将调用`ChannelPipeline`中的下一个`ChannelOutboundHandler`的`connect(ChannelHandlerContext, SocketAddress, ChannelPromise)`方法 |
| `disconnect`    | 将`Channel`断开连接。这将调用`ChannelPipeline`中的下一个`ChannelOutboundHandler`的`disconnect(ChannelHandlerContext, Channel Promise)`方法 |
| `close`         | 将`Channel`关闭。这将调用`ChannelPipeline`中的下一个`ChannelOutboundHandler`的`close(ChannelHandlerContext, ChannelPromise)`方法 |
| `deregister`    | 将`Channel`从它先前所分配的`EventExecutor（即EventLoop）`中注销。这将调用`ChannelPipeline`中的下一个`ChannelOutboundHandler`的`deregister(ChannelHandlerContext, ChannelPromise)`方法 |
| `flush`         | 冲刷`Channel`所有挂起的写入。这将调用`ChannelPipeline`中的下一个`ChannelOutboundHandler`的`flush(ChannelHandlerContext)`方法 |
| `write`         | 将消息写入`Channel`。这将调用`ChannelPipeline`中的下一个`ChannelOutboundHandler`的`write(ChannelHandlerContext, Object msg, ChannelPromise)`方法。注意：这并不会将消息写入底层的Socket，而只会将它放入队列中。要将它写入Socket，需要调用`flush()`或者`writeAndFlush()`方法 |
| `writeAndFlush` | 这是一个先调用`write()`方法再接着调用`flush()`方法的便利方法 |
| `read`          | 请求从`Channel`中读取更多的数据。这将调用`ChannelPipeline`中的下一个`ChannelOutboundHandler`的`read(ChannelHandlerContext)`方法 |

##### **`ChannelHandler`所使用的适配器类**

+ `ChannelHandlerAdapter`
+ `ChannelInboundHandlerAdapter`
+ `ChannelOutboundHandlerAdapter`
+ `ChannelDuplexHandler`

##### **`Bootstrap`**客户端引导类

引导一个客户端只需要一个`EventLoopGroup`。`bind()`方法在与UDP这种无需连接传输方式结合使用时非常有用。对于常规的TCP连接，使用`connect()`方法。

##### **`ServerBootstrap`**服务端引导类

`ServerBootstrap`需要两个`EventLoopGroup`（也可以是同一个实例）

![服务端引导类为何需要两个不同的EventLoopGroup](../../image/netty/服务端引导类为何需要两个不同的EventLoopGroup.png)

第一个`EventLoopGroup`只包含一个`ServerChannel`，代表服务器自身的已绑定到某个本地端口的正在监听的套接字。第二个将包含所有已创建的用来处理传入客户端连接的`Channel`。

与`ServerChannel`相关联的`EventLoopGroup`将分配一个负责为传入连接请求创建`Channel`的`EventLoop`。一旦连接被接受，第二个`EventLoopGroup`就会给它的`Channel`分配一个`EventLoop`。

##### **`ChannelConfig`**

包含了该Channel的所有配置设置

**`ChannelOption`**

#### Netty内置的传输

| 名称       | 包                            | 描述                                                         |
| ---------- | ----------------------------- | ------------------------------------------------------------ |
| `NIO`      | `io.netty.channel.socket.nio` | 使用`java.nio.channels` 包作为基础——基于选择器的方式         |
| `Epoll`    | `io.netty.channel.epoll`      | 由 JNI 驱动的 `epoll()`和非阻塞 IO。这个传输支持只有在Linux 上可用的多种特性，如SO_REUSEPORT，比`NIO` 传输更快，而且是完全非阻塞的 |
| `OIO`      | `io.netty.channel.socket.oio` | 使用`java.net` 包作为基础——使用阻塞流                        |
| `Local`    | `io.netty.channel.local`      | 可以在VM 内部通过管道进行通信的本地传输                      |
| `Embedded` | `io.netty.channel.embedded`   | `Embedded` 传输，允许使用`ChannelHandler` 而又不需要一个真正的基于网络的传输。这在测试你的`ChannelHandler` 实现时非常有用 |

1. `NIO`——非阻塞的I/O

   NIO提供了一个所有I/O操作的全异步的实现。它利用了自NIO子系统被引入JDK1.4时便可用的基于选择器的API。

   选择器背后的基本概念是充当一个注册表，在那里你将可以请求在Channel的状态发生变化时得到通知。可能的变化有：

   + 新的`Channel`已被接受并且就绪；
   + `Channel`连接已经完成；
   + `Channel`有已经就绪可供读取的数据；
   + `Channel`可用于写数据

   **选择器**运行在一个检查状态变化并对其做出相应响应的线程上，在应用程序对状态的改变做出响应后，选择器将会被重置，并将重复这个过程。

   **选择操作的位模式**

   | 名称         | 描述                                                         |
   | ------------ | ------------------------------------------------------------ |
   | `OP_ACCEPT`  | 请求在接受新连接并创建`Channel`时获得通知                    |
   | `OP_CONNECT` | 请求在建立一个连接时获得通知                                 |
   | `OP_READ`    | 请求当数据已经就绪，可以从`Channel`中读取时获得通知          |
   | `OP_WRITE`   | 请求当可以向`Channel`中写入更多的数据时获得通知。<br/>这处理了套接字缓冲区被完全填满时的情况，这种情况通常发生在数据的发送速度比远程节点可处理的速度更快的时候 |

2. `Epoll`——用于Linux的本地非阻塞传输

   Netty为Linux提供了一组NIO API，以一种和它本身的设计更加一致的方式使用epoll，并且以一种更加轻量的方式使用中断。（使用`EpollEventLoopGroup`和`EpollServerSocketChannel`）

3. `OIO`——旧的阻塞I/O

   Netty利用SO_TIMEOUT这个Socket标志，它指定了等待一个I/O操作完成的最大毫秒数。如果操作在指定的时间间隔内没有完成，则将会抛出一个`SocketTimeout Exception`。Netty将捕获这个异常并继续处理循环。

4. Local——用于JVM内部通信的Local传输

   Netty提供了一个Local传输，用于在同一个JVM中运行的客户端和服务端程序之间的异步通信。

5. Embedded传输

   Netty提供了一种额外的传输，使得你可以将一组ChannelHandler作为帮助器类嵌入到其他的ChannelHandler内部。通过这种方式，你将可以扩展一个ChannelHandler的功能，而不需要修改其内部代码。

#### `ByteBuf`

##### `ByteBuf`的使用模式

1. 堆缓冲区

   将数据存储在JVM的堆空间中。这种模式被称为支撑数组（backing array），它能在没有使用池化的情况下提供快速的分配和释放。

   ```java
   private void useByteBufArray() {
           ByteBuf heapBuf = Unpooled.buffer();
           // 检查ByteBuf是否有一个支撑数组
           if (heapBuf.hasArray()) {
               byte[] array = heapBuf.array(); // 如果有，则获取对该数组的引用
               int offset = heapBuf.arrayOffset() + heapBuf.readerIndex(); // 计算第一个字节的偏移量
               int length = heapBuf.readableBytes(); // 获取可读字节数
   //            handleArray(array, offset, length); // 处理array
           }
       }
   ```

2. 直接缓冲区

   避免在每次调用本地I/O操作之前（或者之后）将缓冲区的内容复制到一个中间缓冲区（或者从中间缓冲区把内容复制到缓冲区），而使用的一种对于对象创建所使用的内存分配在堆外内存的模式。

   ```java
   private void useDirectBuf() {
           ByteBuf directBuf = Unpooled.directBuffer();
           // 检查ByteBuf是否由数组支撑。如果不是，则这是一个直接缓冲区
           if (!directBuf.hasArray()) {
               int length = directBuf.readableBytes(); // 获取可读字节数
               byte[] array = new byte[length]; // 分配一个新的数组来保存具有该长度的字节数据
               directBuf.getBytes(directBuf.readerIndex(), array); // 将字节复制到该数组
   //            handleArray(array, 0, length); // 处理array
           }
       }
   ```

3. 复合缓冲区

   为多个`ByteBuf`提供了一个聚合视图（相当于共享同一个`ByteBuf`）。通过`CompositeByteBuf`实现了这个模式，提供了一个将多个缓冲区表示为单个合并缓冲区的虚拟表示。

   ```java
   private void useByteBuf() {
       CompositeByteBuf buf = Unpooled.compositeBuffer(20);
       buf.addComponent(Unpooled.buffer());
       buf.addComponent(Unpooled.buffer());
       buf.component(1).writeInt(12);
   }
   ```

##### ByteBuf的结构

使用**readerIndex**和**writerIndex**两个指针来控制读写操作。

```
	+--------------------------+-------------------------+-----------------------+
    | discardable bytes |  readable bytes  |  writable bytes  |
    |                   			  |     (CONTENT)      |                             |
    +---------------------------+-------------------------+-----------------------+
    |                   			  |                               |                             |
    0      <=           readerIndex   <=      writerIndex    <=      capacity
```

