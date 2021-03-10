1. ApplicationContext和BeanFactory的区别
   + BeanFactory是一个功能比较简单的容器
   + ApplicationContext在其基础上扩展了很多的功能，如
     + MessageSource，提供国际化的消息访问
     + 资源访问，如URL和文件
     + 事件传播
     + 载入多个（有继承关系）上下文，使得每一个上下文都专注于一个特定的层次，比如应用的Web层
2. BeanNameAware、BeanFactoryAware、BeanClassLoaderAware和ApplicationContextAware的作用
3. EntityResolver的用法
    SAX应用程序在解析XML文件时，需要先读取该XML文件上的声明，根据声明去寻找对应的DTD定义，以便对文档进行验证。  
    默认寻找规则是通过网络来下载对应的DTD声明，并进行认证。  
    EntityResolver的作用是项目本身就可以提供一个如何寻找DTD声明的方法。比如将DTD文件方法项目的某处，在实现时直接将该文档读取并返回给SAX即可。这样就避免了通过网络来寻找相应的声明。  
4. BeanDefinition：配置文件<bean>元素标签在spring容器内部的表现形式。<bean>元素标签拥有class、scope、lazy-init等属性，BeanDefinition则提供了相对应的beanClass、scope、lazyInit属性，BeanDefinition和<bean>中的属性是一一对应的。

+ AbstractBeanDefinition
+ RootBeanDefinition、GenericBeanDefinition和ChildBeanDefinition

5. BeanFactory、ObjectFactory和FactoryBean的区别
+ BeanFactory是一个Factory，IOC容器或对象工厂。在Spring中，所有的Bean都是有BeanFactory（也就是IOC容器）进行管理的。负责对bean的创建、访问等工作。
+ FactoryBean是个bean，一个能生产或修饰对象生成的工厂bean。实现了FactoryBean<T>接口的Bean，根据该Bean的Id从BeanFactory获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身，如果想要获取FactoryBean对象，需要在Id前加&符号来获取。
+ ObjectFactory是一个普通的对象工厂接口。和不同的作用域有关。

6. BeanPostProcessor用法

7. BeanFactoryPostProcessor用法

8. InitializingBean接口用法

9. spring事件发布机制

10. Interceptor用法

11. @Transactional注解

    1. 正确设置@Transactional的**propagation**属性：需要注意下面三种propagation可以不启动事务。本来期望目标方法进行事务管理，但是若错误的配置这三种propagation，事务将不会发生回滚。

       1. **TransactionalDefinition.PROPAGATION_SUPPORTS**:如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行
       2. **Transactional.PROPAGATION_NOT_SUPPORT**:以非事务方式运行，如果当前存在事务，则把当前事务挂起
       3. **Transactional.PROPAGATION_NEVER**:以非事务方式运行，如果当前存在事务，则抛出异常

    2. 正确设置@Transactional的**rollbackFor**属性：默认情况下，如果在事务中抛出了**RuntimeException**或者**Error则Spring将回滚事务**；除此之外，Spring不会回滚事务。如果在事务中抛出其他类型的异常，并期望Spring能够回滚事务，可以**指定rollbackFor**。

    3. @Transactional只能应用到public方法才会生效： 这是因为在使用 Spring AOP 代理时，Spring 在调用在 TransactionInterceptor  在目标方法执行前后进行拦截之前，DynamicAdvisedInterceptor（CglibAopProxy 的内部类）的  intercept 方法或 JdkDynamicAopProxy 的 invoke 方法会间接调用  **AbstractFallbackTransactionAttributeSource**（Spring 通过这个类获取@Transactional 注解的事务属性配置属性信息）的 **computeTransactionAttribute** 方法。 

       ```java
       // AbstractFallbackTransactionAttributeSource
       protected TransactionAttribute computeTransactionAttribute(Method method,
           Class<?> targetClass) {
               // Don't allow no-public methods as required.
               if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
                   return null;
               }
       ```
       
    4. 避免Spring的AOP的自调用问题：若同一个类中的其他**没有@Transactional注解的方法**``内部调用``了**有@Transactional注解的方法**，**有@Transactional注解的方法的事务被忽略，不会发生回滚**。这个是由于Spring AOP造成的。为了解决这个问题，可以使用AspectJ取代Spring AOP：
    
       1. 将AspectJ信息添加到xml配置文件
    
       ```xml
       
       <tx:annotation-driven mode="aspectj" />
       <bean id="transactionManager"
       class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
       <property name="dataSource" ref="dataSource" />
       </bean>
       </bean
       class="org.springframework.transaction.aspectj.AnnotationTransactionAspect"
       factory-method="aspectOf">
       <property name="transactionManager" ref="transactionManager" />
       </bean>
       
       ```
    
       2. 在pom文件中添加相关的依赖