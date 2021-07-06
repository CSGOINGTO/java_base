1. /#和$的区别
   1. /#{}是经过预编译的，在动态SQL解析阶段，#{}会被解析成参数标记符?，这样就防止了SQL注入的问题
   2. ${}在动态SQL解析阶段，${}会直接将传入的参数当做字符串填充到sql语句中
   
2. `SqlSession`

   当前的一次会话，提供了很多操纵数据库的方法。

3. 缓存机制

   1. **一级缓存**

      默认是开启的，并且是关闭不了的，**SqlSession级别的缓存，只在一个会话中有效，**也被称为本地缓存。

   2. **二级缓存**

      需要手动开启，基于**namespace(mapper)级别**的缓存

      在SQL映射文件中添加：

      ```sql
      ## 开启缓存，默认是开启的
      <setting name="cacheEnabled" value="true"/>
      
      ## mapper中
      <cache/>
      
      <cache
        eviction="FIFO"
        flushInterval="60000"
        size="512"
        readOnly="true"/>
      ```

      `<cache />`所起到的效果：

      - 映射语句文件中的所有 select 语句的结果将会被缓存。
      - 映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。
      - 缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
      - 缓存不会定时进行刷新（也就是说，没有刷新间隔）。
      - 缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。
      - 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。

      ```java
      public interface Cache {
          String getId();
          void putObject(Object key, Object value);
          Object getObject(Object key);
          Object removeObject(Object key);
          void clear();
          int getSize();
          ReadWriteLock getReadWriteLock();
      }
      ```

