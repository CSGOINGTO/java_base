1. 内部使用的数据结构为红黑树
2. 插入时，根据key的自然排序或者根据映射时提供的`Comparator`进行排序，自提供的`Comparator`在创建`TreeMap`时传入
3. 不允许插入为null的key，如果插入则报`NullPointException`
4. 可以插入为null的value
5. 非线程安全
6. 应用：
    1. 使用TreeMap实现一致性hash算法：http://www.jetchen.cn/consistent-hash/













参考：

1. https://yanglukuan.github.io/2017/09/06/java/Java%E9%9B%86%E5%90%88%E6%A1%86%E6%9E%B6%E4%B9%8BTreeMap%E8%AF%A6%E8%A7%A3/