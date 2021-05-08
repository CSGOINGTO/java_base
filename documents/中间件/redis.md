1. Redis的基本数据类型

   | 类型     | 简介 | 特性 | 场景 | 命令 |
   | :------ | ----------- | ----------- | ----------- | ------- |
   | String(字符串) | 二进制安全 | 可以包含任何数据，比如jpg图片或者序列化的对象，String类型的值最大能存储512MB |      | set、get、incr、incrby |
   | Hash(字典) | 键值对(Key->Value)集合，是一个String类型的值和kv键值对的映射表 | 特别适合存储对象，并且可以像数据库中update一个属性一样只修改某一项属性值。每个hash可以存储2^32-1个键值对 | 存储、读取、修改 | hmset、hmget、hset、hget、hlen、hkeys、hdel |
   | List(列表) | 链表(双向而非循环链表) | 按照插入的顺序排序，可以添加一个元素到列表的头部(左边)或者尾部(右边) | 1. 最新消息排列<br />2. 消息队列 | lpush、rpush、lpop、rpop、rpoplpush、brpoplpush、lrem、blpop |
   | Set(集合) | Hash表实现，元素不重复 | 1. 通过Hash表实现，添加，删除，查找的时间复杂度都是O(1)<br />2. 为集合提供了求交集、并集、差集等操作 | 1. 共同好友<br /> 2. 好友推荐 | sadd、sdiff(差集)、smembers、sinter(交集)、sunion(并集)、spop、srem |
   | Sorted Set(有序集合) | 将Set中的元素增加一个权重参数score，元素按score有序排列 | 数据插入集合时，已经进行天然排序 | 1.排行榜<br />2. 带权重的消息队列 | zadd、zrem、zrange、zincrby、zrank、zscore、zunionstore、zinterstore |
   
2. 大量key在同一时间过期，需要注意什么？

3. redis分布式锁

4. keys获取指定模式的key列表

5. scan命令

6. Redis做异步队列

   一般使用list结构作为队列，rpush生产消息，lpop消费消息。当lpop没有消息的时候，要适当sleep一会再重试。如果不sleep的话，还可以使用blpop命令，在没有消息的时候，它会阻塞指到消息到来。

7. 队列，生产一个消息，多次消费

   使用pub/sub主题订阅者模式，可以实现1：N的消息队列

8. pub/sub主题订阅模式的缺点

   在消费者下线的情况下，生产的消息会丢失，这种需要使用专业的Mq消息队列中间件进行解决

9. Redis实现延时队列

   使用sortedset，时间戳作为score，消息内容作为key，调用zadd来生产消息，消费者用zrangebyscore命令获取N秒之前的数据轮询进行处理

10. Redis持久化

    Redis启动时判断有没有开启AOF持久化，如果开启，则加载AOF文件；如果没有开启，加载RDB文件。

    1. RDB

       全量数据

    2. AOF

       每次操作的日志

11. RDB原理

    fork和cow，fork指redis通过创建子进程来进行RDB操作，cow指copy on write

12. Pipeline有什么好处，为什么要用pipeline
13. Redis同步机制
14. Redis集群










参考：

1. Redis 命令参考：http://doc.redisfans.com/