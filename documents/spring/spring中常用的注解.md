1. @ComponentScan

   配置扫描的类路径，将扫描到的类路径上的需要装配的类自动装配到spring容器中

2. @Component

   作用于类上，通过对类路径扫描来找出需要装配的类，并自动装配到spring容器中，通常使用@ComponentScan注解配合使用

3. @Bean

   作用在方法上，将被标注的方法中返回的对象装配到spring容器中，通常比@Component使用更加灵活

4. @Repository：标记持久层需要自动装配的类

5. @Service：service层需要自动装配的类

6. @Controller：接口层需要自动装配的类

7. @Autowire

8. @Qutifry