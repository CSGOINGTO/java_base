1. **resize()：** 

   > JDK1.8之后，resize时不需要计算当前节点的hashcode，因为Node节点本身就有个属性int hash，JDK1.8之后添加了链表转红黑树的优化，在resize时，元素hash & 当前数组的长度length，每次数组扩容是旧数组长度的2倍，这样相当于把元素hash值的当前高一位放开(比如，元素hash为11010，长度16,11010&1111=1010；扩容后长度为32，11010&11111=1 1010)，这样扩容后的元素的位置要么还在原位置，要么就在原位置+旧数组长度（只需判断高一位是否为1），这样也保证了在扩容之后元素的分散。

   1. **resize()时机：**
      1. 数组为null或者数组长度为0时
      2. 当数组中元素的个数 > threshold时
      3. 数组的长度length小于64，某个链表长度大于8，进入到转换红黑树方法`treeifyBin(tab, hash)`时

2. **链表转为红黑树的时机：**

   1. 发生hash碰撞之后，**将新节点加入到链表中之后**，如果链表长度(加上数组中的元素)  >= 9个，即当链表长度为8时，再加上一个节点之后就会进入到转换红黑树的方法`treeifyBin(tab, hash)`，在这个方法内，判断当前数组的length是否小于`MIN_TREEIFY_CAPACITY(64)`，如果小于的话，进入resize()，否则才进入红黑树的转化操作。因此，默认情况下，链表中的长度最多可以存放10个，当第11个插入之后（链表长度为8个时，新加入节点会触发`treeifyBin(tab, hash)`的执行。在`treeifyBin(tab, hash)`方法内会判断链表的当前长度是否大于64，而数组默认长度为16，因此会触发2次`resize()`操作。即8+2+1，第11个节点插入后，才真正的触发链表转化为红黑树），才会真正的进入链表转红黑树的操作。

      ```java
      for (int binCount = 0; ; ++binCount) {
          // 当p.next为null时，说明当前node节点为链表的最后一个节点，将新节点插入到该节点后面
          if ((e = p.next) == null) {
              // 必须等到新节点加入之后才可以判断是否进入转化红黑树的逻辑
              p.next = newNode(hash, key, value, null);
              // binCount >= 7时(当前链表的长度为9(加上新节点之后))，进入到链型结构转变为树形结构的流程
              if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                  treeifyBin(tab, hash);
              break;
          }
          // 如果链表内的某个节点的hash值与新节点相同，并且新节点的key也与新节点相同，直接结束循环
          if (e.hash == hash &&
              ((k = e.key) == key || (key != null && key.equals(k))))
              break;
          // 相当于p = p.next(e = p.next)
          p = e;
      }
      ```

3. **红黑树转为链表的时机：**

   > 当链表长度小于UNTREEIFY_THRESHOLD(6)时，调用`Node<K,V> untreeify(HashMap<K,V> map)`方法，将红黑树转化为链表

4. **threshold：** threshold = length * Load factor，HashMap所能容纳的最大数据量Node(键值对)个数。超过这个数值之后就resize。默认为12 = 16 * 0.75。

5. **load factor**： 负载因子默认为0.75。如果内存空间多并且对时间效率要求高时，可以降低负载因子的值；如果内存空间紧张而对时间效率要求不高时，可以增加负载因子的值，这个值可以大于1。

6. HashMap的长度length为2的n次方，默认为16，插入新节点时，通过这个节点的hashCode & (length - 1)，既可以得到这个新节点所要插入的位置。因此好的hash函数可以降低hash冲突的概率，使得节点分散的很均匀。

7. **JDK1.7和JDK1.8的区别：**

   1. 最重要的一点是底层结构不一样，1.7是数组+链表，1.8则是数组+链表+红黑树结构;
   2. jdk1.7中当哈希表为空时，会先调用inflateTable()初始化一个数组；而1.8则是直接调用resize()扩容;
   3. 插入键值对的put方法的区别，1.8中会将节点插入到链表尾部，而1.7中是采用头插；
   4. jdk1.7中的hash函数对哈希值的计算直接使用key的hashCode值，而1.8中则是采用key的hashCode异或上key的hashCode进行无符号右移16位的结果，避免了只靠低位数据来计算哈希时导致的冲突，计算结果由高低位结合决定，使元素分布更均匀；
   5. 扩容时1.8会保持原链表的顺序，而1.7会颠倒链表的顺序；而且1.8是在元素插入后检测是否需要扩容，1.7则是在元素插入前；
   6. jdk1.8是扩容时通过hash&cap==0将链表分散，无需改变hash值，而1.7是通过更新hashSeed来修改hash值达到分散的目的；
   7. 扩容策略：1.7中是只要不小于阈值就直接扩容2倍；而1.8的扩容策略会更优化，当数组容量未达到64时，以2倍进行扩容，超过64之后若桶中元素个数不小于7就将链表转换为红黑树，但如果红黑树中的元素个数小于6就会还原为链表，当红黑树中元素不小于32的时候才会再次扩容。

8. **hash(Object key)：**

      ```java
      static final int hash(Object key) {
          int h;
          // 对高16位进行异或操作，增加对hash值的扰动
          return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
      }
      ```

9. **其他注意点：**
   1. 只要保证length的长度是2^n 的话，就可以实现取模运算了。
   2. HashMap实际容量是大于等于初始化的容量中为2次幂的第一个值
   3. HashMap默认的初始化大小为16，之后每次扩充为原来的2倍
   4. HashTable默认的初始大小为11，之后每次扩充为原来的2n+1
   5. 当哈希表的大小为素数时，简单的取模哈希的结果会更加均匀，所以单从这一点上看，HashTable的哈希表大小选择，似乎更高明些。因为hash结果越分散效果越好
   6. 在取模计算时，如果模数是2的幂，那么我们可以直接使用位运算来得到结果，效率要大大高于做除法。所以从hash计算的效率上，又是HashMap更胜一筹
   7. 但是，HashMap为了提高效率使用位运算代替哈希，这又引入了哈希分布不均匀的问题，所以HashMap为解决这问题，又对hash算法做了一些改进，进行了扰动计算



参考：

	1. https://blog.csdn.net/qq_38685503/article/details/88430788
	2. https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650120877&idx=1&sn=401bb7094d41918f1a6e142b6c66aaac&chksm=f36bbf8cc41c369aa44c319942b06ca0f119758b22e410e8f705ba56b9ac6d4042fe686dbed4&scene=21#wechat_redirect