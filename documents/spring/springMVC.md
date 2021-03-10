1. 每一个Web应用都有一个**ServletContext**与之关联。ServletContext对象在应用启动时被创建，在应用关闭的时候被销毁。其在全局范围内有效，类似于应用中的一个全局变量。
2. **contextConfigLocation：**Web和Spring配置文件相结合的一个关键配置
3. **DispatcherServlet：**实现了Servlet接口，包含了SpringMVC的请求逻辑，Spring使用此类拦截Web请求并进行相应的逻辑处理
4. **ContextLoaderListener：**实现了**ServletContextListener**接口，其核心逻辑就是初始化**WebApplicationContext**实例并存放在**ServletContext**中。
5. WebApplicationContext：WebApplicationContext继承自**ApplicationContext**，在ApplicationContext的基础上追加了一些特定于Web的操作及属性，非常类似于我们通过编程的方式使用Spring时，使用的ClassPathXmlApplicationContext类提供的功能。

