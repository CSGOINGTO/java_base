1. java内存区域

   1. 运行时数据区域

      ![jvm运行时数据区域](../../image/java/jvm运行时数据区域.png)

      1. 程序计数器(Program Counter Register)：**当前线程所执行字节码的行号指示器。**

      2. Java虚拟机栈：描述的是Java方法执行的内存模型，**当前线程私有。**

         每个方法在执行的同时都会创建一个栈帧，用于存储局部变量表、操作数栈、动态链接、方法出口等信息。局部变量表存放了编译期可知的各种基本数据类型(boolean、byte、char、short、int、float、long、double)、对象引用和returnAddress类型(指向了一条字节码指令的地址)。

         **该区域会发生两类异常：StackOverflowError和OutOfMemoryError。**

      3. 本地方法栈：**为本地方法(native method)服务**，作用同Java虚拟机栈，**同样也会出现StackOverflowError和OutOfMemoryError异常**

      4. Java堆：**线程共享的区域，存放对象实例。会出现OutOfMemoryError异常。**

      5. 方法区：**线程共享的区域，用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译期编译后的代码等数据。这部分内存区域也会出现OutOfMemoryError异常。**

      6. 运行时常量池：方法区的一部分。存放编译期生成的各种字面量和符号引用。注意，常量不一定只有编译期才能产生，运行期也可能将新的常量放入池中，例如String类的intern()方法。

      7. 直接内存：NIO可以使用Native函数库直接分配堆外内存，然后通过一个存储在堆内的对象作为这块内存的引用，用于提高操作性能。该块内存不受堆内存大小的限制，但是因为是动态申请的，所以也会出现OutOfMemoryError。

