1. 每一个Web应用都有一个**ServletContext**与之关联。ServletContext对象在应用启动时被创建，在应用关闭的时候被销毁。其在全局范围内有效，类似于应用中的一个全局变量。

2. **contextConfigLocation：**Web和Spring配置文件相结合的一个关键配置

3. **DispatcherServlet：**实现了Servlet接口，包含了SpringMVC的请求逻辑，Spring使用此类拦截Web请求并进行相应的逻辑处理

4. **ContextLoaderListener：**实现了Tomcat提供的**ServletContextListener**接口的监听器，其核心逻辑就是通过contextConfigLocation的属性值拿到配置文件的位置（如果没有的配置，则默认为/WEB-INF/applicationContext.xml），`contextInitialized()`方法初始化**WebApplicationContext**实例并存放在**ServletContext**中。

5. **WebApplicationContext：**WebApplicationContext继承自**ApplicationContext**，在ApplicationContext的基础上追加了一些特定于Web的操作及属性，非常类似于我们通过编程的方式使用Spring时，使用的ClassPathXmlApplicationContext类提供的功能。

6. XmlWebApplicationContext:

7. DispatcherServlet只能被声明一次，如何保证webApplicationContext通过构造函数初始化

8. web.xml文件配置servlet参数contextAttribute，默认为WebApplicationContext.class.getName() + ".ROOT"

9. MultipartResolver：主要用来处理文件上传。默认情况下，Spring是没有Multipart处理的，如果需要的话，则需要在web应用的上下文中添加multipart解析器，这样每次请求都会检查是否包含multipart，如果包含，则使用上下文定义的MultipartResolver来解析。

   ```xml
   <bean id="multipartResolver" class="org.Springframework.web.multipart.commons.Commons.MultipartResolver"
         <property name="maximumFileSize">
   		<value>1000000</value>
   	  </property>
   </bean>
   ```

10. SpringMVC国际化方式：

    1. 基于URL参数的配置：AcceptHeaderLocaleResolver
    2. 基于session的配置：SessionLocaleResolver
    3. 基于cookie的配置：CookieLocaleResolver

11. HandlerExceptionResolver

12. HandlerAdapter

13. ViewResolver

14. HandlerInterceptor