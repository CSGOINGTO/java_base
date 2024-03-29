1. AOP（ Aspect Oriented Programming ）：AOP是一种面向切面编程的思想，其典型的应用即Spring的事物机制，日志记录。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。将日志记录、性能统计、安全控制、事物处理、异常处理等代码从业务逻辑代码中划分出来，进而在改变这些代码时不会影响到业务逻辑的代码。

   Spring框架和AspectJ都对其进行了实现，其中AspectJ的实现更为便捷，功能更为强大，且使用更为方便，而且还支持注解开发，因此Spring将AspectJ中对AOP的实现引入到了自己的框架中，使得在Spring中可以使用aspectJ语法来实现AOP。

2. Spring-AOP和AspectJ的区别和联系

   + **AspectJ**：AspectJ是AOP的Java实现方案。
     + AspectJ是一个代码生成工具，其中AspectJ语法就是用来定义代码生成规则的语法。基于自己的**语法编译工具**，编译的结果是Java Class文件
     + AspectJ有自己的类加载器，支持在**类加载时**织入切面，即所谓的LTW机制
     + AspectJ同样也支持运行时织入，运行时织入是基于动态代理的机制（默认机制）
   + **Spring-AOP**：也是AOP的实现方案
     + 支持在**运行期**基于动态代理的方式将aspect织入目标代码来实现AOP。
     + 对切入点支持有限，对于static方法和final方法都无法支持AOP（因为此类方法无法生成代理类）
     + 只支持IOC容器管理的Bean，其他普通的Java类无法支持AOP
   + **联系和区别**：Spring-AOP中，我们同样可以使用类似AspectJ的注解来实现AOP的功能，但是，**使用AspectJ注解时，AOP的实现方式还是Spring AOP，并不依赖于AspectJ的编译器或者织入器。**

3. AOP织入的时期

   + 编译期：在编译时，由编译器把切面调用编译进字节码，这种方式需要定义新的关键字并扩展编译器，`AspectJ`就扩展了Java编译器，使用关键`aspect`来实现织入
   + 类加载器：在目标类被加载到JVM时，通过一个特殊的类加载器，对目标类的字节码重新“增强”
   + 运行期：目标对象和切面都是普通Java类，通过JVM的动态代理功能或者第三方库实现运行期动态织入

4. 代理类的生成方式：JDK动态代理、CGLIB代理和expose-proxy

   + **JDK动态代理：** 其代理对象必须是某个接口的实现，它是通过运行期间创建一个接口的实现类来完成对目标对象的代理。**使用JDK动态代理的方式：**
     
     + 自定义实现InvocationHandler接口的类
     
     + 声明要代理的对象接口，并在构造函数中将其设置

     + `invoke`方法，实现代理的逻辑
     
     + `getProxy`方法，获取代理之后的对象
     
       ```java
       public class MyInvocationHandler implements InvocationHandler {
       
           // 被代理的对象
           private final Object object;
       
           // 构造器初始化被代理的对象
           public MyInvocationHandler(Object object) {
               this.object = object;
           }
       
           @Override
           public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               System.out.println("My invocationHandler invoke begin...");
               System.out.println("proxy: " + proxy.getClass().getName());
               System.out.println("method: " + method.getName());
               // 打印调用方法的参数
               if (args != null) {
                   for (Object o : args) {
                       System.out.println("arg: " + o);
                   }
               }
               if (method.getName().equals("toString")) {
                   return proxy.getClass().toString();
               } else if (method.getName().equals("hashCode")) {
                   return proxy.getClass().hashCode();
               } else {
                   method.invoke(object, args);
               }
               System.out.println("My invocationHandler invoke end...");
               return proxy.getClass();
           }
       }
       ```
     
       ```java
       public class Main {
           public static void main(String[] args) {
               Student student = new Student();
               ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
               Person person = Proxy.newProxyInstance(classLoader, 
                                      Student.class.getInterfaces(), 
                                      new MyInvocationHandler(student));
               person.say("我是动态代理!");
           }
       }
       ```
     
   + **CGLIB代理：** 实现原理类似于JDK动态代理，只是它在运行期间生成的代理对象是针对目标类扩展的子类。CGLIB是高效的代码生成包，底层是依靠ASM（一个小而快的字节码处理框架）操作字节码生成新的类，性能比JDK强。

   + **expose-proxy：** 有时候目标对象内部的自我调节将无法实施切面中的增强，比如this.内部方法，内部方法也要增强，就可以使用这样方式解决。

5. JDK代理和CJLIB代理方式总结

   + 如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP
   + 如果目标对象实现了接口，可以**强制使用CJLIB实现AOP**
     + 添加CGLIB库，Spring_Home/cglib/*.jar
     + 在Spring配置文件中加入<aop:aspect-autoproxy proxy-target-class="true" />
   + 如果目标对象没有实现接口，必须采用CGLIB库，Spring会自动在JDK动态代理和CGLIB之间转换

6. JDK动态代理和CGLIB字节码生成的区别？

   + JDK动态代理只能对实现了接口的类生成代理，而不能针对类
   + CGLIB是针对类实现代理，主要是对针对的类生成一个子类，覆盖其中的方法，因为是继承，所以该类或方法最好不要声明成final

7. 静态AOP：主要是在**虚拟机启动时**通过改变目标对象字节码的方式来完成对目标对象的增强。它与动态代理相比具有更高的效率，因为在动态代理调用的过程中，还需要一个动态创建代理类并代理目标对象的过程，而静态代理则是在启动时便完成了字节码增强，当系统再次调用目标类时，与调用正常的类无差别，所以在效率上会相对高一些。

   + **实现方式**
     + 加载时织入（Load-Time Weaving, LTW）：在虚拟机载入字节码文件时动态织入AspectJ切面。