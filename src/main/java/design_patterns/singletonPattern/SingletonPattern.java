package design_patterns.singletonPattern;

/**
 * 单例模式：一个类只有唯一的实例，在创建日志类实例，数据库连接实例等会使用到。
 * 分为三种：饿汉模式、懒汉模式、双重测锁机制的单例模式
 */
public class SingletonPattern {
    /**
     * 饿汉模式：实例在类加载的时候初始化
     */
    private static SingletonPattern singletonPattern = new SingletonPattern();
    private SingletonPattern(){}
    public static SingletonPattern getSingletonPattern() {
        return singletonPattern;
    }
}

/**
 * 懒汉模式
 */
class SingletonPattern1{
    /*
     * 只有在使用这个实例的时候才会初始化
     */
    private static SingletonPattern1 singletonPattern1 = null;
    private SingletonPattern1(){}
    public static SingletonPattern1 getSingletonPattern1() {
        // 在多线程的时候回出现问题，所以可以使用synchronized进行同步操作，但是这样的话效率会比较低
        // 所有才有了双重检测
        if (singletonPattern1 == null) {
            singletonPattern1 = new SingletonPattern1();
        }
        return singletonPattern1;
    }
}

/**
 * 双重测锁机制的单例模式（Double-checked locking）
 */
class SingletonPattern2 {
    private static SingletonPattern2 singletonPattern2 = null;
    private SingletonPattern2(){}
    public static SingletonPattern2 getSingletonPattern2() {
        if (singletonPattern2 == null) {
            // 在多线程的情况下，有些线程可能走完上一步之后就没有了时间片
            // 等到再次进入的时候，因为在同步代码块中有了再次的判断，所以
            // 它会及时的发现实例有没有被初始化。
            // 如果有的话，那么就会直接跳出同步快，直接返回之前已经初始化好的实例；
            // 如果没有，那么就直接初始化实例。
            synchronized (SingletonPattern2.class) {
                if (singletonPattern2 == null) {
                    singletonPattern2 = new SingletonPattern2();
                }
            }
        }
        return singletonPattern2;
    }
}
