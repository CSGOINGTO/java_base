1. @ComponentScan

   配置扫描的类路径，将扫描到的类路径上的需要装配的类自动装配到spring容器中

2. @Component

   作用于类上，通过对类路径扫描来找出需要装配的类，并自动装配到spring容器中，通常使用@ComponentScan注解配合使用

3. @Bean

   作用在方法或注解上，将被标注的方法中返回的对象装配到spring容器中，通常比@Component使用更加灵活

4. @Repository：标记持久层需要自动装配的类

5. @Service：service层需要自动装配的类

6. @Controller：接口层需要自动装配的类

7. @Autowire(required default true)

   Spring包中的注解，按照类型自动装配，可以在Constructor(构造器)、Method(方法)、Parameter(方法参数)、Field(字段、枚举常量)和Annotation_type(注解)上使用。在spring容器内按照类型查找对应的bean，如果找到的结果为1个，则直接将结果装配给指定的数据；如果找个结果为多个的话，按照名称来查找；如果查询结果为空，抛出异常（可以将required设置为false解决，但是有可能会出现空指针异常）。

   `AutoWiredAnnotationBeanPostProcessor`后置处理器是处理@AutoWired注解的。

8. @Resource(name, type)

   JDK中的注解，默认按照名称自动装配，也可以设置按照类型自动装配，可以在Type(接口、类、枚举、注解)、Field(字段、枚举常量)和Method(方法)上使用。

   + 装配顺序
     + 如果同时指定了name和type，则从spring容器中找个唯一的bean进行装配，找不到则抛出异常
     + 如果指定name，则从上下文中找到id为指定name的bean进行装配，找不到抛出异常
     + 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个都会抛出异常
     + 如果没有指定name，也没有指定type，则默认按照name的方式进行装配(name默认为字段名或属性名)，如果没有匹配，则按照类型进行装配

9. @Qualifier(value)

   Spring中的注解，可以在Parameter(方法参数)、Method(方法)、Field(字段、枚举常量)、Type(接口、类、枚举、注解)和Annotation_type(注解)上使用。

   当有多个类型相同的bean时，指定名称来确定所要装配的bean。