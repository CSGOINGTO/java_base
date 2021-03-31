1. 继承自HashMap

2. 存储节点Entry继承自HashMap中的Node，不同之处在于多了两个`before`和`after`的属性，可以构成双向链表

   ```java
   static class Entry<K,V> extends Node<K,V> {
       Entry<K,V> before, after;
       Entry(int hash, K key, V value, Node<K,V> next) {
           super(hash, key, value, next);
       }
   }
   ```

3. 类中有两个属性Entry<K, V> head和Entry<K, V> tail两个属性，用于记录节点插入的顺序

4. 在新建存储节点时（put时），会调用linkNodeLast(Entry<K, V> p)方法，该方法会将新节点加入到双向链表中

   ```java
   Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
       Entry<K,V> p =
           new Entry<K,V>(hash, key, value, e);
       // 将新节点加入到双向链表中
       linkNodeLast(p);
       return p;
   }
   ```

   ```java
   // link at the end of list
   private void linkNodeLast(Entry<K,V> p) {
       Entry<K,V> last = tail;
       tail = p;
       if (last == null)
           head = p;
       else {
           p.before = last;
           last.after = p;
       }
   }
   ```

5. 遍历时迭代器iterator中的`nextNode()`方法是通过遍历双向链表实现的

   ```java
   final Entry<K,V> nextNode() {
       Entry<K,V> e = next;
       if (modCount != expectedModCount)
           throw new ConcurrentModificationException();
       if (e == null)
           throw new NoSuchElementException();
       current = e;
       next = e.after;
       return e;
   }
   ```

6. remove()方法调用了afterNodeRemoval(Node<K,V> e)方法

   ```java
   public final void remove() {
       Node<K,V> p = current;
       if (p == null)
           throw new IllegalStateException();
       if (modCount != expectedModCount)
           throw new ConcurrentModificationException();
       current = null;
       K key = p.key;
       removeNode(hash(key), key, null, false, false);
       expectedModCount = modCount;
   }
   ```

   ```java
   void afterNodeRemoval(Node<K,V> e) { // unlink
       Entry<K,V> p =
           (Entry<K,V>)e, b = p.before, a = p.after;
       p.before = p.after = null;
       if (b == null)
           head = a;
       else
           b.after = a;
       if (a == null)
           tail = b;
       else
           a.before = b;
   }
   ```

7. key值可以为null
8. value值也可以为null
9. 非线程安全