### java_base

本仓库记录了本人从事Java开发工作后学习的内容，其中包括

+ Java基本语法
+ 基本的设计模式
+ 常用的框架的学习
+ 面试学习文档

这个仓库中的内容适用于Java中级水平以下的程序员使用（主要是本人的水平有限。。。），你可以把它当做一个

+ Java技术字典
+ 基本技术的使用语法
+ 基本框架的脚手架

**如有一些内容有错误的话,欢迎大家批评指正~**

------

> **仓库内容链接**

#### 1. documents模块

1. [java文件夹](documents/java)

   其中记录了Java基础、常用集合、多线程和JVM等常见的Java知识点

2. [中间件文件夹](documents/中间件)（后续还会继续添加新的中间件内容）

   其中记录了[rabbitMQ](documents/中间件/rabbitMQ.md)和[redis中间件](documents/中间件/redis.md)的常见的知识点

3. [四大本](documents/四大本)

   四大本指的是计算机专业最基础且最重要的四门课程：

   1. [计算机网络](documents/四大本/计网.md)
   2. 计算机组成原理
   3. [操作系统](documents/四大本/操作系统.md)
   4. 数据结构

4. [工具](documents/工具)

   这里主要包括日常开发过程中常用的工具：

   1. [git](documents/工具/git.md)
   2. [maven](documents/工具/maven.md)

5. [数据库](documents/数据库)

   包括数据库基础和常见的sql调优等

6. [框架](documents/框架)

   目前包括：

   1. [myBatis](documents/框架/myBatis)
   2. [spring](documents/框架/spring)

7. [笔试](documents/笔试)

   主要记录了常用的[算法技巧](documents/笔试/技巧.md)和在[笔试中常用的Java类库](documents/笔试/类库.md)

8. [设计模式](documents/设计模式)

   记录了常用的设计模式的定义和使用

9. [有意思网站](documents/有意思网站.md)

   可以快速加深我们对技术底层的理解和对某些技术快速入门的网站

------

#### 2. base_knowledge模块

重点模块，该模块使用Maven进行管理，并使用了Lombok插件。

该模块分为两大内容：

1. Java相关的基础知识
   1. [Java程序启动时，各个语句的启动顺序](base_knowledge/src/main/java/base_knowledge/base)
   2. [注解](base_knowledge/src/main/java/base_knowledge/annot)
   3. [类加载器](base_knowledge/src/main/java/base_knowledge/classLoad)
   4. [常用集合](base_knowledge/src/main/java/base_knowledge/collection)
   5. [枚举](base_knowledge/src/main/java/base_knowledge/enumdemo)
   6. [泛型](base_knowledge/src/main/java/base_knowledge/generic)
   7. [反射](base_knowledge/src/main/java/base_knowledge/reflect)
   8. [代理](base_knowledge/src/main/java/base_knowledge/proxy)
   9. [阻塞队列](base_knowledge/src/main/java/base_knowledge/blocking_queue)
   10. [jvm](base_knowledge/src/main/java/base_knowledge/jvm)
   11. [多线程](base_knowledge/src/main/java/base_knowledge/Multi_Thread)
   12. [网络](base_knowledge/src/main/java/base_knowledge/network)
   13. [rpc](base_knowledge/src/main/java/base_knowledge/rpc)
   14. [JDK源码解析](base_knowledge/src/main/java/base_knowledge/sourcecode/java)
       1. 基础
          1. [Object](base_knowledge/src/main/java/base_knowledge/sourcecode/java/lang/Object.java)
       2. 集合
          1. [Map](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/Map.java)
          2. [AbstractMap](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/AbstractMap.java)
          3. [HashMap](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/HashMap.java)
          4. [LinkedHashMap](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/LinkedHashMap.java)
          5. [AbstractList](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/AbstractList.java)
          6. [ArrayList](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/ArrayList.java)
          7. [LinkedList](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/LinkedList.java)
       3. 多线程
          1. [Thread](base_knowledge/src/main/java/base_knowledge/sourcecode/java/lang/Thread.java)
          2. [Lock](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/concurrent/locks/Lock.java)
          3. [Condition](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/concurrent/locks/Condition.java)
          4. [AbstractQueuedSynchronizer](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/concurrent/locks/AbstractQueuedSynchronizer.java)
          5. [LockSupport](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/concurrent/locks/LockSupport.java)
          6. [ReentrantLock](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/concurrent/locks/ReentrantLock.java)
          7. [ReentrantReadWriteLock](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/concurrent/locks/ReentrantReadWriteLock.java)
          8. [CountDownLatch](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/concurrent/CountDownLatch.java)
          9. [CyclicBarrier](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/concurrent/CyclicBarrier.java)
          10. [Semphore](base_knowledge/src/main/java/base_knowledge/sourcecode/java/util/concurrent/Semaphore.java)
2. 常用的设计模式的代码实现
   1. [适配器模式](base_knowledge/src/main/java/design_patterns/adapter)
   2. [装饰模式](base_knowledge/src/main/java/design_patterns/decorator)
   3. [观察者模式](base_knowledge/src/main/java/design_patterns/observer)
   4. [代理模式](base_knowledge/src/main/java/design_patterns/proxy)
   5. [责任链模式](base_knowledge/src/main/java/design_patterns/responsibityChain)
   6. [策略模式](base_knowledge/src/main/java/design_patterns/strategy)

------

#### 3. spring_learn模块

主要是为了方便debug spring源码

------

#### 4. spring_boot_learn模块

spring-boot中常用的一些注解的使用

------

#### 5. my_springboot_starter模块

自定义的springboot-starter模块

------

#### 6. kafka_learn模块

springboot与kafka基本的使用

------

#### 7. rabbit_learn模块

RabbitMQ官网中所介绍的几种使用方式的代码实现
