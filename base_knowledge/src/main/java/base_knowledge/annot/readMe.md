### 注解
1. @interface声明一个注解类
2. 原生注解：
    1. @Target声明注解目标：
        + ElementType.TYPE,类型（类，接口，注解，Enum）
        + ElementType.FIELD, 属性域
        + ElementType.METHOD, 方法域
        + ElementType.PARAMETER,参数（JDK1.8后用TYPE_PARAMETER）
        + ElementType.CONSTRUCTOR,构造函数
        + ElementType.LOCAL_VARIABLE,局部变量
        + ElementType.ANNOTATION_TYPE,注解类型
        + ElementType.PACKAGE,包
        + ElementType.TYPE_USE,(JDK1.8后用)
    2. @Retention：说明该注解类的生命周期。
        + SOURCE：源码级别，不会被抛弃;
        + CLASS:被编译器保留到编译后的类文件，但是会被虚拟机丢弃;
        + RUNTIME:保留到运行时，可以被反射读取。
    3. @Documented：表示注解能否被javadoc处理，并保留到文档中
    4. @Inherited：如果一个使用了@Inherited修饰的注解被用于一个class，则这个注解将被用于该class的子类
    5. @native：用来标记native的属性，只对属性有效，且只在代码中使用，一般用于给IDE工具做提示用
    6. @Repeatable：可重复注解的注解，允许在同一个申明类型（类，属性，方法）中多次使用同一个注解
    
    