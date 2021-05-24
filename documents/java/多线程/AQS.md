**AQS（AbstractQueuedSynchronizer）：** 抽象队列同步器

> 内部维护了一个`volitile int state`表示共享资源（state状态的修改都是通过CAS），一个CLH FIFO线程等待队列（会初始化一个头结点）。
>

1. 数据结构：
   1. Node节点：
      1. int waitStatus
         1. CANCELLED(1)：取消状态，当前线程timeout或被中断，设置为取消状态
         2. SIGNAL(-1)：当前节点的后继节点处于等待状态，当前节点的线程如果释放或者取消了同步状态，通知后续节点
         3. CONDITION(-2)：等待队列的等待状态，当调用signal()后，CONDITION状态的节点将由等待队列转移到同步队列中，等待获取同步锁
         4. PROPAGATE(-3)：共享模式下，前继节点不仅会唤醒后继节点，同时也可能唤醒后继节点
         5. 0，初始状态
      2. Node prev前置节点
      3. Node next后置节点
      4. Node nextWaiter，作为等待队列使用时，存放的是后继节点；作为同步队列使用时，存放EXCLUSIVE,SHARED标识当前节点是独占模式还是共享模式
      5. Thread thread
   
2. 重点方法：
   1. **Node addWaiter(Node mode)：** 将线程封装成Node节点，加入到CLH队列的队尾，并将Node节点信息返回。如果CLH队列中存在节点，则直接将新节点加入到队尾，否则进入到enq(Node node)方法中

       ```java
       private Node addWaiter(Node mode) {
               Node node = new Node(Thread.currentThread(), mode);
               // Try the fast path of enq; backup to full enq on failure
               Node pred = tail;
               // 如果当前队列中已经存在节点（与enq中的部分操作相同）
               if (pred != null) {
                   node.prev = pred;
                   if (compareAndSetTail(pred, node)) {
                       pred.next = node;
                       return node;
                   }
               }
               // 将新节点node加入到等待队列中
               enq(node);
               // 将新节点返回
               return node;
           }
       ```

   2. **Node enq(final Node node)：** 将节点加入到队尾，如果需要初始化时，进行初始化

       ```java
       private Node enq(final Node node) {
           for (;;) {
               // t当前队列的尾部节点
               Node t = tail;
               // 如果尾部节点为null，说明当前队列为空，需要进行初始化
               if (t == null) { // Must initialize
                   // 将head原子性设置为一个新的节点
                   if (compareAndSetHead(new Node()))
                       // 将尾部节点也指向当前这个新的节点（相当于添加了一个头节点）
                       tail = head;
               } else { // 队列中已经存在节点
                   // t (next-&gt;) node
                   // t (prev&lt;-) node
                   // 将新节点node的前置节点设置为当前队列的尾部节点
                   // 因为新节点首先设置它的前置节点，并且原子性的将新节点设置为当前等待队列的尾结点，因此遍历等待队列时，应该从尾结点向前进行遍历
                   node.prev = t;
                   // 将新节点node原子性的设置为当前队列的尾部节点
                   // t虽然是局部变量，但是cas保证了替换尾结点是否会成功，如果不成功的话，因为tail是volatile修饰的，因此下次循环时，t又会赋值为最新的尾部节点tail
                   if (compareAndSetTail(t, node)) {
                       // 尾部节点的后置设置为node
                       t.next = node;
                       // 将之前的尾部节点返回
                       return t;
                   }
               }
           }
       }
       ```

   3. **boolean acquireQueued(final Node node, int arg)：** 判断当前传入的node节点是否为head，如果是则尝试加锁，**加锁成功后，将当前节点设置为head节点，然后将之前的head节点置为null，方便GC（上一个节点把该节点唤醒之后，在此时才把上一个节点从CLH队列中移除）**。如果加锁失败，或者Node的前置节点不是head节点，通过`boolean shouldParkAfterFailedAcquire(Node pred, Node node)`方法将head节点的waitStatus修改为SIGNAL=-1，然后执行`boolean parkAndCheckInterrupt()`方法，调用LockSupport.park()挂起当前线程。

      ```java
      final boolean acquireQueued(final Node node, int arg) {
          boolean failed = true;
          try {
              boolean interrupted = false;
              for (;;) {
                  // 获取到当前节点node的前置节点p
                  final Node p = node.predecessor();
                  // 如果前置节点p为队列中的头节点的话，尝试获取一次锁
                  if (p == head && tryAcquire(arg)) {
                      // 重新设置头节点
                      setHead(node);
                      p.next = null; // help GC
                      failed = false;
                      return interrupted;
                  }
                  // 第一次循环中进入shouldParkAfterFailedAcquire，将状态转变为SIGNAL
                  // 后面的循环中，如果有node的waitStatus的状态为SIGNAL，则进入parkAndCheckInterrupt
                  if (shouldParkAfterFailedAcquire(p, node) &&
                      parkAndCheckInterrupt())
                      interrupted = true;
              }
          } finally {
              if (failed)
                  cancelAcquire(node);
          }
      }
      ```

   4. **boolean shouldParkAfterFailedAcquire(Node pred, Node node)：** 将当前节点的前置节点的`waitStatus`进行修改

      ```java
      private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
          // 获取前置节点的waitStatus
          int ws = pred.waitStatus;
          // 如果前置节点的waitStatus为SIGNAL
          if (ws == Node.SIGNAL)
              /*
               * This node has already set status asking a release
               * to signal it, so it can safely park.
               */
              return true;
          // ws的waitStatus为CANCELLED（1）
          if (ws > 0) {
              /*
               * Predecessor was cancelled. Skip over predecessors and
               * indicate retry.
               */
              // 从当前节点的前置节点往前遍历，忽略前置节点的waitStatus>0的情况
              do {
                  node.prev = pred = pred.prev;
              } while (pred.waitStatus > 0);
              pred.next = node;
          } else {
              /*
               * waitStatus must be 0 or PROPAGATE.  Indicate that we
               * need a signal, but don't park yet.  Caller will need to
               * retry to make sure it cannot acquire before parking.
               */
              // 将处于其他状态节点的状态原子性的转为SIGNAL
              compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
          }
          return false;
      }
      ```

   5. **boolean hasQueuedPredecessors()：** 判断CLH队列中是否存在其他等待的节点，如果存在其他等待节点，则需要将当前节点加入到CLH队列的队尾，返回`true`；队列中没有节点或者仅有一个节点是当前线程创建的节点，返回`false`。

      **注意：**`head != tail && h.next  == null`的情况，**在enq方法中，因为CAS设置head和tail之后的操作不是原子性的，所以存在某一时刻存在head.next为null的情况，这种情况说明有一个节点正在插入到CLH队列**，也要返回true，需要将当前节点加入到CLH队列的队尾。

      而在head != tail的情况下，只需判断head.next.thread是否与当前线程相同，就可以判断出当前线程是否需要加入到CLH队列（如果不相同，说明需要加入；如果相同，则不需要加入）。

      ```java
      // enq(Node node)方法
      for (;;) {
          Node t = tail;
          if (t == null) { // Must initialize
              if (compareAndSetHead(new Node())) // 执行到这时，head->new Node() tail = null
                  tail = head; // 执行到此时head才和tail指向new Node()
          } else {
              node.prev = t;// 此时t与head和tail都指向new Node()
              if (compareAndSetTail(t, node)) {// 执行到这时，tail->node head.next = null
                  t.next = node;// 执行到这时，head.next -> node
                  return t;
              }
          }
      }
      ```

      ```java
      public final boolean hasQueuedPredecessors() {
          // The correctness of this depends on head being initialized
          // before tail and on head.next being accurate if the current
          // thread is first in queue.
          Node t = tail; // Read fields in reverse initialization order
          Node h = head;
          Node s;
          return h != t &&
              ((s = h.next) == null || s.thread != Thread.currentThread());
      }
      ```

   6. **protected boolean tryAcquire(int arg)：** 独占方式获取锁。尝试获取资源，成功则返回true，失败则返回false

   7. **protected boolean tryAcquireShared(int arg)：** 共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源

   8. **protected boolean tryRelease(int arg)：** 独占方式释放锁。尝试释放资源，成功则返回true，失败则返回false

   9. **protected boolean tryReleaseShared(int arg)：** 共享方式。尝试释放资源，如果释放后允许唤醒后续等待结点返回true，否则返回false

3. 具体实现
   1. **ReentrantLock**

      1. lock()流程
      2. unlock()流程

   2. **CountDownLatch：** 利用AQS中的state作为共享量

      1. 声明CountDownLatch时，在构造函数中传入int值，作为state的初始值
      2. countDown()，将state-1，并判断-1后的state是否为0，如果为0的话，则进入等待队列中，LockSupport.unPark(node.thread)唤醒等待中的线程节点
      3. await()，只要state不为0，就将当前线程封装成node节点，加入到等待队列的队尾，并调用LockSupport.park(this)方法将当前线程阻塞

   3. **CyclicBarrier：** 利用ReemtrantLock和Condition协助来完成

      1. 声明CyclicBarrier时，可以传入int和Runnable
      2. await()，当int值为0时，自动执行声明时传入的Runnbale，并将当前线程唤醒；当int值不为0时，将当前线程封装成node节点，加入到等待队列的队尾，阻塞当前线程

   4. **Semaphore：** 利用AQS中的state作为共享量，并且可以设置其公平性，共享锁

      1. 声明Semaphore时，可以传入一个int值，表示资源的访问量，还可以同时传入boolean值，表示这个Semaphore是否是公平的，默认为非公平的
      2. acquire()，判断共享量available - 需求量acquires之后，是否小于0，如果小于0，则将当前线程封装成Node节点，加入到等待队列的队尾，阻塞当前线程
      3. release()，释放需求量acquires，CAS将available共享量+acquires ，并唤醒等待队列中队头的线程

   5. **Condition：** JDK1.5之后新加的类，必须要和ReentrantLock配合使用，用来替代Object的wait()、notify()实现线程间的协作，相比使用Object的wait()、notify()，使用Condition的await()、signal()这种方式实现线程间协作更加安全高效。使用AQS中的condition队列实现。Node的状态为CONDITION

      1. await()：释放锁，并将当前线程封装成Node，加入到condition队列中
      2. signal()：将condition队列中的节点唤醒

   6. **ReentrantReadWriteLock:** 一个资源能够被多个线程访问，或者被一个写线程访问，但是不能同时存在读写或写写线程

      1. 与ReentrantLock的区别：

         1. ReentrantLock是独享锁；ReentrantReadWriteLock内部有读锁和写锁两种，其中读锁是共享锁，写锁是独享锁

      2. ReadLock和WriteLock共享变量：

         ```java
         static final int SHARED_SHIFT   = 16;
         static final int SHARED_UNIT    = (1 << SHARED_SHIFT); // 1+16位0
         static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1; // 16位1
         static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
         
         /** Returns the number of shared holds represented in count  */
         static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
         /** Returns the number of exclusive holds represented in count  */
         static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }
         ```

         1. 读锁是通过将c右移16位获得，写锁通过和16位的1做与运算获得
         2. 由于16位多大为65535，所以读锁和写锁最多可以获取65535个

      3. WriteLock和ReentrantLock获取锁的区别

         1. 区别在于`tryAcquire(int acquires)`方法的实现的不同，主要的区别在于写锁调用了`exclusiveCount()`方法来获取是否存在写锁，然后通过c != 0 和 w == 0判断是否存在读锁

            ```java
            // 写锁获取锁
            protected final boolean tryAcquire(int acquires) {
                /*
                 * Walkthrough:
                 * 1. If read count nonzero or write count nonzero
                 *    and owner is a different thread, fail.
                 * 2. If count would saturate, fail. (This can only
                 *    happen if count is already nonzero.)
                 * 3. Otherwise, this thread is eligible for lock if
                 *    it is either a reentrant acquire or
                 *    queue policy allows it. If so, update state
                 *    and set owner.
                 */
                Thread current = Thread.currentThread();
                // 获取共享变量state
                int c = getState();
                // w当前写锁的次数
                int w = exclusiveCount(c);
                // 有读锁或者写锁
                if (c != 0) {
                    // (Note: if c != 0 and w == 0 then shared count != 0)
                    // 写锁为0(证明有读锁)，或者持有写锁的线程不是当前线程
                    if (w == 0 || current != getExclusiveOwnerThread())
                        return false;
                    // 判断是否超过获取写锁的最大值65535
                    if (w + exclusiveCount(acquires) > MAX_COUNT)
                        throw new Error("Maximum lock count exceeded");
                    // Reentrant acquire
                    // 当前线程持有锁，为重入锁，state + acquires
                    setState(c + acquires);
                    return true;
                }
                // CAS操作失败
                if (writerShouldBlock() ||
                    !compareAndSetState(c, c + acquires))
                    return false;
                setExclusiveOwnerThread(current);
                return true;
            }
            ```

      4. ReadLock和Semaphore获取锁的区别

         1. 区别主要在于`int tryAcquireShared(int unused)`方法实现的不同，主要区别在于读锁在获取锁时，需要获取到当前写锁的数量，如果没有写锁占用并且不超过最大获取数量都可以尝试获取锁。

            ```java
            protected final int tryAcquireShared(int unused) {
                /*
                 * Walkthrough:
                 * 1. If write lock held by another thread, fail.
                 * 2. Otherwise, this thread is eligible for
                 *    lock wrt state, so ask if it should block
                 *    because of queue policy. If not, try
                 *    to grant by CASing state and updating count.
                 *    Note that step does not check for reentrant
                 *    acquires, which is postponed to full version
                 *    to avoid having to check hold count in
                 *    the more typical non-reentrant case.
                 * 3. If step 2 fails either because thread
                 *    apparently not eligible or CAS fails or count
                 *    saturated, chain to version with full retry loop.
                 */
                // 获取当前线程
                Thread current = Thread.currentThread();
                // 获取当前AQS的status
                int c = getState();
                // exclusiveCount(c)获取status低16位的值，判断是否有写锁占用
                if (exclusiveCount(c) != 0 &&
                    getExclusiveOwnerThread() != current)
                    return -1;
                // 获取读锁次数
                int r = sharedCount(c);
                if (!readerShouldBlock() &&  // 读锁不需要阻塞
                    r < MAX_COUNT && // 读锁小于最大读锁数量
                    compareAndSetState(c, c + SHARED_UNIT)) {// CAS操作尝试获取读锁，即高位+1
                    if (r == 0) { // 当前线程第一个，并且第一次获取读锁
                        firstReader = current;
                        firstReaderHoldCount = 1;
                    } else if (firstReader == current) {// 当前线程也是第一次获取读锁的线程
                        firstReaderHoldCount++;
                    } else {
                        // 当前线程不是第一个获取读锁的线程，放入线程本地变量
                        // 获取最近一次记录的HoldCounter，此缓存是为了提高效率，不用每次都去ThreadLocal中获取
                        HoldCounter rh = cachedHoldCounter;
                        // 判断当前线程是不是最近一次记录的HoldCounter
                        if (rh == null || rh.tid != getThreadId(current))
                            // 如果不是，则去Sync中的ThreadLocal中获取，然后再放到缓存中
                            cachedHoldCounter = rh = readHolds.get();
                        // 如果count计数为0，则初始化HoldCounter
                        else if (rh.count == 0)
                            readHolds.set(rh);
                        // 当前线程的重入次数+1
                        rh.count++;
                    }
                    return 1;
                }
                return fullTryAcquireShared(current);
            }
            ```
      
      5. **锁降级，WriteLock没有释放的时候，可以获取ReadLock。** 保证了数据的可见性。当前线程修改了数据之后，因为写锁还没有释放，因此其他线程都获取不到写锁，而当前线程也获取到了读锁，在写锁释放之后，就可以立即读取到最新写入的数据。

         而锁的升级（ReadLock没有释放的时候，获取WriteLock）是不支持的。

   7. **StampedLock：** JDK1.8加入的，对读写锁ReentReadWriteLock的增强，优化了读锁、写锁的访问，同时使读写锁之间可以互相转换，更细粒度控制开发。































参考：

1. https://www.cnblogs.com/wang-meng/p/12816829.html
2. https://www.cnblogs.com/waterystone/p/4920797.html
3. https://juejin.cn/post/6844903680370556941

