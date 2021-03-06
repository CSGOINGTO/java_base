package base_knowledge.sourcecode.java.util.concurrent.locks;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@code Lock} implementations provide more extensive locking
 * operations than can be obtained using {@code synchronized} methods
 * and statements.  They allow more flexible structuring, may have
 * quite different properties, and may support multiple associated
 * {@link Condition} objects.
 * Lock实现提供了比使用synchronized方法和语句更广泛的锁操作。它们允许更灵活的结构，可能
 * 具有完全不同的属性，并且可能支持多个关联Condition对象。
 *
 * <p>A lock is a tool for controlling access to a shared resource by
 * multiple threads. Commonly, a lock provides exclusive access to a
 * shared resource: only one thread at a time can acquire the lock and
 * all access to the shared resource requires that the lock be
 * acquired first. However, some locks may allow concurrent access to
 * a shared resource, such as the read lock of a {@link ReadWriteLock}.
 * 锁是一种工具，用于控制多个线程对共享资源的访问。通常，锁提供对共享资源的独占访问：一次只能有一个线程获得锁，
 * 所有对共享资源的访问都需要先获得锁。然而，一些锁可能允许并发访问共享资源，例如ReadWriteLock的读锁。
 *
 * <p>The use of {@code synchronized} methods or statements provides
 * access to the implicit monitor lock associated with every object, but
 * forces all lock acquisition and release to occur in a block-structured way:
 * when multiple locks are acquired they must be released in the opposite
 * order, and all locks must be released in the same lexical scope in which
 * they were acquired.
 * 使用synchronized方法或语句提供了与每个对象关联的隐式的监视器锁，但会强制所有锁的获取和释放在同一个代码块结构中：
 * 当一个代码加了多个锁，必须以锁的相反的顺序释放锁，同时加锁和释放锁必须在同一个上下文中，这就是所谓的scope。
 *
 * <p>While the scoping mechanism for {@code synchronized} methods
 * and statements makes it much easier to program with monitor locks,
 * and helps avoid many common programming errors involving locks,
 * there are occasions where you need to work with locks in a more
 * flexible way. For example, some algorithms for traversing
 * concurrently accessed data structures require the use of
 * &quot;hand-over-hand&quot; or &quot;chain locking&quot;: you
 * acquire the lock of node A, then node B, then release A and acquire
 * C, then release B and acquire D and so on.  Implementations of the
 * {@code Lock} interface enable the use of such techniques by
 * allowing a lock to be acquired and released in different scopes,
 * and allowing multiple locks to be acquired and released in any
 * order.
 * Synchronized的scope机制，使得使用monitor lock编程变得简单，同时避免了涉及锁的普遍错误（common programming errors involving locks.）
 *
 * synchronized(A){
 *      synchronized(B){
 * 		    synchronized(C){
 *          }
 *      }
 * }
 * 在上面的代码块中获取锁是按照A，B，C的顺序，而释放锁是按照C,B,A的顺序，这个顺序是严格确定的，没法改变的。
 * 而有的时候，我们可能要以获取A，B，然后释放A，获取C，然后释放B获取D等等。这种顺序使用锁，显然，对于synchronized是无法做到的。
 * 而Lock接口的实现，却可以做到以任意顺序释放锁。
 *
 * <p>With this increased flexibility comes additional
 * responsibility. The absence of block-structured locking removes the
 * automatic release of locks that occurs with {@code synchronized}
 * methods and statements. In most cases, the following idiom
 * should be used:
 * 虽然释放锁非常灵活，但是对于Lock来说，必须显式的释放锁，而synchronized会自动释放锁，使用Lock的通用代码格式如下：
 *
 *  <pre> {@code
 * Lock l = ...;
 * l.lock();
 * try {
 *   // access the resource protected by this lock
 * } finally {
 *   l.unlock();
 * }}</pre>
 *
 * When locking and unlocking occur in different scopes, care must be
 * taken to ensure that all code that is executed while the lock is
 * held is protected by try-finally or try-catch to ensure that the
 * lock is released when necessary.
 * 当加锁和解锁发生在不同的scope时，必须要小心的确保在持有锁时执行的所有的代码都受到try-finally
 * 或try-catch的保护，以确保在必要时释放锁。
 *
 * <p>{@code Lock} implementations provide additional functionality
 * over the use of {@code synchronized} methods and statements by
 * providing a non-blocking attempt to acquire a lock ({@link
 * #tryLock()}), an attempt to acquire the lock that can be
 * interrupted ({@link #lockInterruptibly}, and an attempt to acquire
 * the lock that can timeout ({@link #tryLock(long, TimeUnit)}).
 * Lock的实现通过提供一个非阻塞的尝试获取锁tryLock()，尝试获取锁并可以被中断lockInterruptibly()，
 * 和一个尝试获取锁并可以超时的tryLock(long, TimeUnit)提供额外的功能在使用同步方法和语句。
 *
 * <p>A {@code Lock} class can also provide behavior and semantics
 * that is quite different from that of the implicit monitor lock,
 * such as guaranteed ordering, non-reentrant usage, or deadlock
 * detection. If an implementation provides such specialized semantics
 * then the implementation must document those semantics.
 * Lock类还可以提供与隐式监视器锁完全不同的行为和语义，例如保证顺序，不可重入，死锁检测。
 * 如果一个实现提供了这种专门的语义，那么该实现必须记录这些语义。
 *
 * <p>Note that {@code Lock} instances are just normal objects and can
 * themselves be used as the target in a {@code synchronized} statement.
 * Acquiring the
 * monitor lock of a {@code Lock} instance has no specified relationship
 * with invoking any of the {@link #lock} methods of that instance.
 * It is recommended that to avoid confusion you never use {@code Lock}
 * instances in this way, except within their own implementation.
 * 注意，Lock实例只是一个普通的对象，本身可以在同步语句中作为目标。
 * 获取lock实例的监视器锁与调用该实例的任何锁实例的任何lock方法没有指定关系。
 * 为了避免混淆，建议不要以这种方式使用lock实例，除非是它自己的实现中。
 *
 * <p>Except where noted, passing a {@code null} value for any
 * parameter will result in a {@link NullPointerException} being
 * thrown.
 *
 * <h3>Memory Synchronization</h3>
 *
 * <p>All {@code Lock} implementations <em>must</em> enforce the same
 * memory synchronization semantics as provided by the built-in monitor
 * lock, as described in
 * <a href="https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html#jls-17.4">
 * The Java Language Specification (17.4 Memory Model)</a>:
 * <ul>
 * <li>A successful {@code lock} operation has the same memory
 * synchronization effects as a successful <em>Lock</em> action.
 *
 * <li>A successful {@code unlock} operation has the same
 * memory synchronization effects as a successful <em>Unlock</em> action.
 * </ul>
 *
 * Unsuccessful locking and unlocking operations, and reentrant
 * locking/unlocking operations, do not require any memory
 * synchronization effects.
 *
 * <h3>Implementation Considerations</h3>
 *
 * <p>The three forms of lock acquisition (interruptible,
 * non-interruptible, and timed) may differ in their performance
 * characteristics, ordering guarantees, or other implementation
 * qualities.  Further, the ability to interrupt the <em>ongoing</em>
 * acquisition of a lock may not be available in a given {@code Lock}
 * class.  Consequently, an implementation is not required to define
 * exactly the same guarantees or semantics for all three forms of
 * lock acquisition, nor is it required to support interruption of an
 * ongoing lock acquisition.  An implementation is required to clearly
 * document the semantics and guarantees provided by each of the
 * locking methods. It must also obey the interruption semantics as
 * defined in this interface, to the extent that interruption of lock
 * acquisition is supported: which is either totally, or only on
 * method entry.
 *
 * <p>As interruption generally implies cancellation, and checks for
 * interruption are often infrequent, an implementation can favor responding
 * to an interrupt over normal method return. This is true even if it can be
 * shown that the interrupt occurred after another action may have unblocked
 * the thread. An implementation should document this behavior.
 *
 * @see ReentrantLock
 * @see Condition
 * @see ReadWriteLock
 *
 * @since 1.5
 * @author Doug Lea
 */
public interface Lock {

    /**
     * Acquires the lock.
     * 获取锁
     *
     * <p>If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until the
     * lock has been acquired.
     * 如果该锁是不可用的，则当前线程将被禁用以用于线程调度，并处于休眠状态，直到获取锁为止
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>A {@code Lock} implementation may be able to detect erroneous use
     * of the lock, such as an invocation that would cause deadlock, and
     * may throw an (unchecked) exception in such circumstances.  The
     * circumstances and the exception type must be documented by that
     * {@code Lock} implementation.
     * Lock实现可能能够检测锁的错误使用，比如会导致死锁的调用，并在这种情况下抛出（未检测的）异常。
     * Lock实现必须记录环境和异常类型。
     */
    void lock();

    /**
     * Acquires the lock unless the current thread is
     * {@linkplain Thread#interrupt interrupted}.
     * 获取锁，除非当前线程被中断。
     *
     * <p>Acquires the lock if it is available and returns immediately.
     * 如果锁是可用的，则立即返回。
     *
     * <p>If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until
     * one of two things happens:
     * 如果该锁不是可用的，那么当前线程将被禁用以用于线程调度，并处于休眠状态，直到下面两种情况之一发生：
     *
     * <ul>
     * <li>The lock is acquired by the current thread; or
     * 当前线程获取的锁
     *
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of lock acquisition is supported.
     * 其他线程调用了当前线程的interrupts，并且支持中断获取锁。
     *
     * </ul>
     *
     * <p>If the current thread:
     * 如果当前线程：
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * 在进入此方法时设置了其中断状态
     * <li>is {@linkplain Thread#interrupt interrupted} while acquiring the
     * lock, and interruption of lock acquisition is supported,
     * 获取锁时调用了interrupted，并且支持中断获取锁。
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     * 那么将抛出InterruptedException，并且当前线程的中断状态将被清除。
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The ability to interrupt a lock acquisition in some
     * implementations may not be possible, and if possible may be an
     * expensive operation.  The programmer should be aware that this
     * may be the case. An implementation should document when this is
     * the case.
     *
     * <p>An implementation can favor responding to an interrupt over
     * normal method return.
     *
     * <p>A {@code Lock} implementation may be able to detect
     * erroneous use of the lock, such as an invocation that would
     * cause deadlock, and may throw an (unchecked) exception in such
     * circumstances.  The circumstances and the exception type must
     * be documented by that {@code Lock} implementation.
     *
     * @throws InterruptedException if the current thread is
     *         interrupted while acquiring the lock (and interruption
     *         of lock acquisition is supported)
     */
    void lockInterruptibly() throws InterruptedException;

    /**
     * Acquires the lock only if it is free at the time of invocation.
     * 仅在调用时锁是空闲的情况下获取锁。
     *
     * <p>Acquires the lock if it is available and returns immediately
     * with the value {@code true}.
     * 如果该锁是可用的，那么将立即获取该锁，并返回true。
     *
     * If the lock is not available then this method will return
     * immediately with the value {@code false}.
     * 如果该锁是不可用的，那么该方法将立即返回false。
     *
     * <p>A typical usage idiom for this method would be:
     *  <pre> {@code
     * Lock lock = ...;
     * if (lock.tryLock()) {
     *   try {
     *     // manipulate protected state
     *   } finally {
     *     lock.unlock();
     *   }
     * } else {
     *   // perform alternative actions
     * }}</pre>
     *
     * This usage ensures that the lock is unlocked if it was acquired, and
     * doesn't try to unlock if the lock was not acquired.
     *
     * @return {@code true} if the lock was acquired and
     *         {@code false} otherwise
     */
    boolean tryLock();

    /**
     * Acquires the lock if it is free within the given waiting time and the
     * current thread has not been {@linkplain Thread#interrupt interrupted}.
     * 如果锁在给定的等待时间内是空闲的，并且当前线程没有被中断，则获取锁。
     *
     * <p>If the lock is available this method returns immediately
     * with the value {@code true}.
     * 如果该锁是可用的，那么该方法立即返回true。
     *
     * If the lock is not available then
     * the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until one of three things happens:
     * 如果该锁不可用，那么当前线程将变得不可用以用于线程调度的目的，并处于休眠状态，直到下面三种情况之一发生：
     *
     * <ul>
     * <li>The lock is acquired by the current thread; or
     * 当前线程获取到该锁；
     *
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of lock acquisition is supported; or
     * 其他线程调用了该线程的中断方法，并且中断锁获取是被支持的；
     *
     * <li>The specified waiting time elapses
     * 指定的等待时间已经到了。
     *
     * </ul>
     *
     * <p>If the lock is acquired then the value {@code true} is returned.
     * 如果该锁被获取到，那么直接返回true。
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while acquiring
     * the lock, and interruption of lock acquisition is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p>If the specified waiting time elapses then the value {@code false}
     * is returned.
     * If the time is
     * less than or equal to zero, the method will not wait at all.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The ability to interrupt a lock acquisition in some implementations
     * may not be possible, and if possible may
     * be an expensive operation.
     * The programmer should be aware that this may be the case. An
     * implementation should document when this is the case.
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return, or reporting a timeout.
     *
     * <p>A {@code Lock} implementation may be able to detect
     * erroneous use of the lock, such as an invocation that would cause
     * deadlock, and may throw an (unchecked) exception in such circumstances.
     * The circumstances and the exception type must be documented by that
     * {@code Lock} implementation.
     *
     * @param time the maximum time to wait for the lock
     * @param unit the time unit of the {@code time} argument
     * @return {@code true} if the lock was acquired and {@code false}
     *         if the waiting time elapsed before the lock was acquired
     *
     * @throws InterruptedException if the current thread is interrupted
     *         while acquiring the lock (and interruption of lock
     *         acquisition is supported)
     */
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    /**
     * Releases the lock.
     * 释放该锁。
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>A {@code Lock} implementation will usually impose
     * restrictions on which thread can release a lock (typically only the
     * holder of the lock can release it) and may throw
     * an (unchecked) exception if the restriction is violated.
     * Any restrictions and the exception
     * type must be documented by that {@code Lock} implementation.
     */
    void unlock();

    /**
     * Returns a new {@link Condition} instance that is bound to this
     * {@code Lock} instance.
     * 返回一个新的Condition实例在当前Lock实例的范围
     *
     * <p>Before waiting on the condition the lock must be held by the
     * current thread.
     * 在等待该Condition之前，锁必须由当前线程持有。
     *
     * A call to {@link Condition#await()} will atomically release the lock
     * before waiting and re-acquire the lock before the wait returns.
     * Condition的await()方法的调用将在等待之前原子性的释放该锁，并在等待返回之前重新获取锁。
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The exact operation of the {@link Condition} instance depends on
     * the {@code Lock} implementation and must be documented by that
     * implementation.
     *
     * @return A new {@link Condition} instance for this {@code Lock} instance
     * @throws UnsupportedOperationException if this {@code Lock}
     *         implementation does not support conditions
     */
    Condition newCondition();
}
