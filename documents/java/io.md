#### IO流

------

> 字节流

1. `InputStream`和`OutputStream`分别是读字节流和写字节流的基类，其中定义了最基本的读写操作。

   ```java
   /**
   * InputStream
   **/
   
   // 该方法返回读取到的下一个字节，返回值为-1和0--255。
   // 注意：返回的类型是int，是为了与结束标记-1和真正有意义的255作区分（Byte的-1二进制为1111 1111，而255的二进制也是0 1111 1111，因此int可以表示255，同时二进制全1表示-1结束标记）
   public abstract int read() throws IOException;
   
   // 在当前流读取位置上打一个标记。
   // 注意：不是抽象方法，因为有些流不支持这种操作
   public synchronized void mark(int readlimit) {}
   
   // 重置读取指针到标记位
   public synchronized void reset() throws IOException {
       throw new IOException("mark/reset not supported");
   }
   
   // 判断当前流是否支持mark/reset操作
   public boolean markSupported() {
       return false;
   }
   
   // 跳过几个字节，返回实际跳过的字节数
   public long skip(long n) throws IOException{
   	...   
   }
   
   
   ```

   ```java
   /**
   * OutputStream
   **/
   
   // 将指定的字节写入到此输出流
   // 注意参数类型是int，实际上int的高24位会被忽略
   public abstract void write(int b) throws IOException;
   
   // 强制将已经写入到输入流中的内容写到设备中
   public void flush() throws IOException {
   }
   ```

