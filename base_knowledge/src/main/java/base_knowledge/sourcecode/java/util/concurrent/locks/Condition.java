package base_knowledge.sourcecode.java.util.concurrent.locks;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * {@code Condition} factors out the {@code Object} monitor
 * methods ({@link Object#wait() wait}, {@link Object#notify notify}
 * and {@link Object#notifyAll notifyAll}) into distinct objects to
 * give the effect of having multiple wait-sets per object, by
 * combining them with the use of arbitrary {@link java.util.concurrent.locks.Lock} implementations.
 * Where a {@code Lock} replaces the use of {@code synchronized} methods
 * and statements, a {@code Condition} replaces the use of the Object
 * monitor methods.
 * Condition将Object监视方法（wait(), notify(), notifyAll()）分解为不同的对象，从而通过与任意Lock实现结合使用，使每个对象具有多个等待集。
 * 在使用Lock代替了同步方法和语句的情况下，可以使用Condition替代Object监视器方法。
 *
 * <p>Conditions (also known as <em>condition queues</em> or
 * <em>condition variables</em>) provide a means for one thread to
 * suspend execution (to &quot;wait&quot;) until notified by another
 * thread that some state condition may now be true.  Because access
 * to this shared state information occurs in different threads, it
 * must be protected, so a lock of some form is associated with the
 * condition. The key property that waiting for a condition provides
 * is that it <em>atomically</em> releases the associated lock and
 * suspends the current thread, just like {@code Object.wait}.
 * Condition（也被称为条件队列或条件变量）为一个线程提供了一种暂停执行（等待）的方法，
 * 直到另一个线程通知某个状态条件可能为真为止。
 * 由于对这种共享状态信息的访问发生在不同的线程中，因此必须对其进行保护，因此某种形式的锁与条件相关联。
 * 等待条件的关键属性是原子性的释放相关的锁并挂起当前线程，就像Object.wait一样。
 *
 * <p>A {@code Condition} instance is intrinsically bound to a lock.
 * To obtain a {@code Condition} instance for a particular {@link java.util.concurrent.locks.Lock}
 * instance use its {@link java.util.concurrent.locks.Lock#newCondition newCondition()} method.
 * 一个Condition实例本质上绑定到一个锁。要获取特定锁实例的Condition实例，请使用lock的newCondition()方法。
 *
 * <p>As an example, suppose we have a bounded buffer which supports
 * {@code put} and {@code take} methods.  If a
 * {@code take} is attempted on an empty buffer, then the thread will block
 * until an item becomes available; if a {@code put} is attempted on a
 * full buffer, then the thread will block until a space becomes available.
 * We would like to keep waiting {@code put} threads and {@code take}
 * threads in separate wait-sets so that we can use the optimization of
 * only notifying a single thread at a time when items or spaces become
 * available in the buffer. This can be achieved using two
 * {@link Condition} instances.
 * 在下面的例子中，假设我们有一个有界的缓冲区，支持put和take方法。
 * 如果take方法试图操作一个空的缓冲，那么这个线程将会阻塞，直到有一条变得可用；如果put方法试图操作一个满的缓冲，
 * 那么这个线程将会阻塞，直到有一个空间变得可用。我们希望保持等待Put的线程和Take的线程在单独的wait-set中，以便我们可以使用这样的优化：
 * 当缓冲区的项目或空间可用时，只通知单个线程。我们可以通过使用两个Condition实例来实现。
 *
 * <pre>
 * class BoundedBuffer {
 *   <b>final Lock lock = new ReentrantLock();</b>
 *   final Condition notFull  = <b>lock.newCondition(); </b>
 *   final Condition notEmpty = <b>lock.newCondition(); </b>
 *
 *   final Object[] items = new Object[100];
 *   int putptr, takeptr, count;
 *
 *   public void put(Object x) throws InterruptedException {
 *     <b>lock.lock();
 *     try {</b>
 *       while (count == items.length)
 *         <b>notFull.await();</b>
 *       items[putptr] = x;
 *       if (++putptr == items.length) putptr = 0;
 *       ++count;
 *       <b>notEmpty.signal();</b>
 *     <b>} finally {
 *       lock.unlock();
 *     }</b>
 *   }
 *
 *   public Object take() throws InterruptedException {
 *     <b>lock.lock();
 *     try {</b>
 *       while (count == 0)
 *         <b>notEmpty.await();</b>
 *       Object x = items[takeptr];
 *       if (++takeptr == items.length) takeptr = 0;
 *       --count;
 *       <b>notFull.signal();</b>
 *       return x;
 *     <b>} finally {
 *       lock.unlock();
 *     }</b>
 *   }
 * }
 * </pre>
 *
 * (The {@link java.util.concurrent.ArrayBlockingQueue} class provides
 * this functionality, so there is no reason to implement this
 * sample usage class.)
 * （ArrayBlockingQueue类提供了这个功能，因此实现这个简单用法的类没有什么意义。）
 *
 * <p>A {@code Condition} implementation can provide behavior and semantics
 * that is
 * different from that of the {@code Object} monitor methods, such as
 * guaranteed ordering for notifications, or not requiring a lock to be held
 * when performing notifications.
 * If an implementation provides such specialized semantics then the
 * implementation must document those semantics.
 * condition的实现可以提供与Object监视器方法不同的行为和语义，比如保证通知的顺序，或者在执行通知时不需要持有lock。
 * 如果一个实现了提供这种专门语义，那么该实现必须记录这个语义。
 *
 * <p>Note that {@code Condition} instances are just normal objects and can
 * themselves be used as the target in a {@code synchronized} statement,
 * and can have their own monitor {@link Object#wait wait} and
 * {@link Object#notify notification} methods invoked.
 * Acquiring the monitor lock of a {@code Condition} instance, or using its
 * monitor methods, has no specified relationship with acquiring the
 * {@link Lock} associated with that {@code Condition} or the use of its
 * {@linkplain #await waiting} and {@linkplain #signal signalling} methods.
 * It is recommended that to avoid confusion you never use {@code Condition}
 * instances in this way, except perhaps within their own implementation.
 * 注意，Condition实例只是普通的对象，他们本身可以作为synchronized语句的目标，并且可以调用它们自己的监视器的wait和notify方法。
 * 获取一个Condition实例的监视器锁，或者是使用它的监视器方法，与获取哪个Condition关联的Lock或者使用它的waiting和signalling方法没有指定的关系。
 * 建议不要以这种容易混淆的方式使用Condition实例，除非是在它们自己的实现中。
 *
 * <p>Except where noted, passing a {@code null} value for any parameter
 * will result in a {@link NullPointerException} being thrown.
 * 除非另有说明，传入null值作为任何参数将会抛出NullPointerException。
 *
 * <h3>Implementation Considerations</h3>
 * 实现的注意事项
 *
 * <p>When waiting upon a {@code Condition}, a &quot;<em>spurious
 * wakeup</em>&quot; is permitted to occur, in
 * general, as a concession to the underlying platform semantics.
 * This has little practical impact on most application programs as a
 * {@code Condition} should always be waited upon in a loop, testing
 * the state predicate that is being waited for.  An implementation is
 * free to remove the possibility of spurious wakeups but it is
 * recommended that applications programmers always assume that they can
 * occur and so always wait in a loop.
 * 当在一个Condition上wait时，虚假唤醒是允许且可能发生的，通常情况下，这是对基础平台语义的让步。
 * 这对大多数应用程序几乎没有实质上的影响，因为Condition始终应该在循环中等待，测试正在等待的状态条件。
 * 实现可以自由地消除虚假唤醒的可能性，但建议应用程序员始终假定它们会发生，因此始终在循环中等待。
 *
 * <p>The three forms of condition waiting
 * (interruptible, non-interruptible, and timed) may differ in their ease of
 * implementation on some platforms and in their performance characteristics.
 * In particular, it may be difficult to provide these features and maintain
 * specific semantics such as ordering guarantees.
 * Further, the ability to interrupt the actual suspension of the thread may
 * not always be feasible to implement on all platforms.
 * Condition的三种wait形式（可中断，不可中断，定时）在某些平台上实现的难易程度和它们的表现特点有一定的不同，
 * 实际上，提供这些特性并保留特殊的语义（例如保证顺序）是困难的。甚至，在实际已经挂起的线程上进行中断并不是在
 * 所有的平台上都行得通的。
 *
 * <p>Consequently, an implementation is not required to define exactly the
 * same guarantees or semantics for all three forms of waiting, nor is it
 * required to support interruption of the actual suspension of the thread.
 * 因此，实际不需要为所有三种等待形式定义万千相同的保证或语义，也不需要支持线程实际挂起的中断。
 *
 * <p>An implementation is required to
 * clearly document the semantics and guarantees provided by each of the
 * waiting methods, and when an implementation does support interruption of
 * thread suspension then it must obey the interruption semantics as defined
 * in this interface.
 * 实现类需要清楚地注明语义和每个wait方法提供的保证，并且当实现类声明支持在挂起的线程上触发中断的时候，
 * 它就必须遵守Condition接口定义的这个中断语义。
 *
 * <p>As interruption generally implies cancellation, and checks for
 * interruption are often infrequent, an implementation can favor responding
 * to an interrupt over normal method return. This is true even if it can be
 * shown that the interrupt occurred after another action that may have
 * unblocked the thread. An implementation should document this behavior.
 * 由于中断通常意味着取消，而且对中断的检查通常并不频繁，因此实现可以倾向于对中断的响应而不是正常的方法返回。
 * 即使可以证明中断发生在可能解除了线程阻塞的另一个动作之后，这也是正确的。实现应该记录这种行为。
 *
 * @since 1.5
 * @author Doug Lea
 */
public interface Condition {

    /**
     * Causes the current thread to wait until it is signalled or
     * {@linkplain Thread#interrupt interrupted}.
     * 导致当前线程进入等待，直到它被唤醒或Thread的interrupted方法被调用。
     *
     * <p>The lock associated with this {@code Condition} is atomically
     * released and the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until <em>one</em> of four things happens:
     * 与这个Condition相关联的lock被原子性释放，当前线程出于线程调度的目的被禁用，处于休眠状态，直到发生下面4中情况中的一种：
     *
     * <ul>
     * <li>Some other thread invokes the {@link #signal} method for this
     * {@code Condition} and the current thread happens to be chosen as the
     * thread to be awakened; or
     * 其他的线程为这个Condition执行signal方法，并且当前线程恰好被选为要被唤醒的线程。
     *
     * <li>Some other thread invokes the {@link #signalAll} method for this
     * {@code Condition}; or
     * 其他线程为这个Condition执行signalAll方法。
     *
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of thread suspension is supported; or
     * 其他线程中断当前线程，并且支持线程挂起中断。
     *
     * <li>A &quot;<em>spurious wakeup</em>&quot; occurs.
     * 假唤醒发生。
     *
     * </ul>
     *
     * <p>In all cases, before this method can return the current thread must
     * re-acquire the lock associated with this condition. When the
     * thread returns it is <em>guaranteed</em> to hold this lock.
     * 在所有情况下，在方法返回之前，当前线程必须重新获取与此Condition关联的lock。
     * 当线程返回时，它是保证持有这个锁的。
     *
     * <p>If the current thread:
     * 如果当前线程：
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while waiting
     * and interruption of thread suspension is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared. It is not specified, in the first
     * case, whether or not the test for interruption occurs before the lock
     * is released.
     * 在进入此方法时设置了中断状态；或者当前线程等待时调用了interrupted方法，并且支持线程挂起中断。
     *
     * <p><b>Implementation Considerations</b>
     * 实现注意事项
     *
     * <p>The current thread is assumed to hold the lock associated with this
     * {@code Condition} when this method is called.
     * 当调用此方法时，假设当前线程持有与此Condition关联的锁。
     *
     * It is up to the implementation to determine if this is
     * the case and if not, how to respond. Typically, an exception will be
     * thrown (such as {@link IllegalMonitorStateException}) and the
     * implementation must document that fact.
     * 由具体的实现决定是否如此，如果不是，如何响应。
     * 通常，会抛出一个异常（例如IllegalMonitorStateException），实现必须记录这个实现。
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return in response to a signal. In that case the implementation
     * must ensure that the signal is redirected to another waiting thread, if
     * there is one.
     * 实现可以倾向于响应中断，而不是响应signal的常规方法返回。
     * 在这种情况下，实现必须确保将signal重定向到另一个等待线程（如果有的话）。
     *
     * @throws InterruptedException if the current thread is interrupted
     *         (and interruption of thread suspension is supported)
     */
    void await() throws InterruptedException;

    /**
     * Causes the current thread to wait until it is signalled.
     * 导致当前线程进入等到，直到signalled
     *
     * <p>The lock associated with this condition is atomically
     * released and the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until <em>one</em> of three things happens:
     * 与该condition相关联的lock的释放是原子性的，为了线程调度的目的当前线程变得不可用，直到下面3种情况之一发生：
     *
     * <ul>
     * <li>Some other thread invokes the {@link #signal} method for this
     * {@code Condition} and the current thread happens to be chosen as the
     * thread to be awakened; or
     * 其他的线程为这个Condition执行signal方法，当前线程刚好被选择为要被唤醒的线程
     *
     * <li>Some other thread invokes the {@link #signalAll} method for this
     * {@code Condition}; or
     * 其他的线程为这个Condition执行signalAll方法
     *
     * <li>A &quot;<em>spurious wakeup</em>&quot; occurs.
     * 虚假唤醒发生
     *
     * </ul>
     *
     * <p>In all cases, before this method can return the current thread must
     * re-acquire the lock associated with this condition. When the
     * thread returns it is <em>guaranteed</em> to hold this lock.
     * 在所有情况下，在方法返回之前，当前线程必须重新获取与此Condition关联的lock。
     * 当线程返回时，它是保证持有这个锁的。
     *
     * <p>If the current thread's interrupted status is set when it enters
     * this method, or it is {@linkplain Thread#interrupt interrupted}
     * while waiting, it will continue to wait until signalled. When it finally
     * returns from this method its interrupted status will still
     * be set.
     * 如果当进入这个方法时，当前线程的中断状态是设置的，或者当等待时interrupted，它将会继续等待直到signal。
     * 当最终从这个方法返回，它的中断状态将继续保持设置。
     *
     * <p><b>Implementation Considerations</b>
     * 实现注意事项
     *
     * <p>The current thread is assumed to hold the lock associated with this
     * {@code Condition} when this method is called.
     * It is up to the implementation to determine if this is
     * the case and if not, how to respond. Typically, an exception will be
     * thrown (such as {@link IllegalMonitorStateException}) and the
     * implementation must document that fact.
     * 当前线程假设当该方法被调用时，与该Condition相关联的lock是持有的。
     * 由实现决定是否如此，如果不是，如何响应。通常，会抛出一个异常（例如IllegalMonitorStateException），实现必须记录这个事实。
     *
     */
    void awaitUninterruptibly();

    /**
     * Causes the current thread to wait until it is signalled or interrupted,
     * or the specified waiting time elapses.
     *
     * <p>The lock associated with this condition is atomically
     * released and the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until <em>one</em> of five things happens:
     * <ul>
     * <li>Some other thread invokes the {@link #signal} method for this
     * {@code Condition} and the current thread happens to be chosen as the
     * thread to be awakened; or
     * <li>Some other thread invokes the {@link #signalAll} method for this
     * {@code Condition}; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of thread suspension is supported; or
     * <li>The specified waiting time elapses; or
     * <li>A &quot;<em>spurious wakeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In all cases, before this method can return the current thread must
     * re-acquire the lock associated with this condition. When the
     * thread returns it is <em>guaranteed</em> to hold this lock.
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while waiting
     * and interruption of thread suspension is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared. It is not specified, in the first
     * case, whether or not the test for interruption occurs before the lock
     * is released.
     *
     * <p>The method returns an estimate of the number of nanoseconds
     * remaining to wait given the supplied {@code nanosTimeout}
     * value upon return, or a value less than or equal to zero if it
     * timed out. This value can be used to determine whether and how
     * long to re-wait in cases where the wait returns but an awaited
     * condition still does not hold. Typical uses of this method take
     * the following form:
     *
     *  <pre> {@code
     * boolean aMethod(long timeout, TimeUnit unit) {
     *   long nanos = unit.toNanos(timeout);
     *   lock.lock();
     *   try {
     *     while (!conditionBeingWaitedFor()) {
     *       if (nanos <= 0L)
     *         return false;
     *       nanos = theCondition.awaitNanos(nanos);
     *     }
     *     // ...
     *   } finally {
     *     lock.unlock();
     *   }
     * }}</pre>
     *
     * <p>Design note: This method requires a nanosecond argument so
     * as to avoid truncation errors in reporting remaining times.
     * Such precision loss would make it difficult for programmers to
     * ensure that total waiting times are not systematically shorter
     * than specified when re-waits occur.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The current thread is assumed to hold the lock associated with this
     * {@code Condition} when this method is called.
     * It is up to the implementation to determine if this is
     * the case and if not, how to respond. Typically, an exception will be
     * thrown (such as {@link IllegalMonitorStateException}) and the
     * implementation must document that fact.
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return in response to a signal, or over indicating the elapse
     * of the specified waiting time. In either case the implementation
     * must ensure that the signal is redirected to another waiting thread, if
     * there is one.
     *
     * @param nanosTimeout the maximum time to wait, in nanoseconds
     * @return an estimate of the {@code nanosTimeout} value minus
     *         the time spent waiting upon return from this method.
     *         A positive value may be used as the argument to a
     *         subsequent call to this method to finish waiting out
     *         the desired time.  A value less than or equal to zero
     *         indicates that no time remains.
     * @throws InterruptedException if the current thread is interrupted
     *         (and interruption of thread suspension is supported)
     */
    long awaitNanos(long nanosTimeout) throws InterruptedException;

    /**
     * Causes the current thread to wait until it is signalled or interrupted,
     * or the specified waiting time elapses. This method is behaviorally
     * equivalent to:
     *  <pre> {@code awaitNanos(unit.toNanos(time)) > 0}</pre>
     *
     * @param time the maximum time to wait
     * @param unit the time unit of the {@code time} argument
     * @return {@code false} if the waiting time detectably elapsed
     *         before return from the method, else {@code true}
     * @throws InterruptedException if the current thread is interrupted
     *         (and interruption of thread suspension is supported)
     */
    boolean await(long time, TimeUnit unit) throws InterruptedException;

    /**
     * Causes the current thread to wait until it is signalled or interrupted,
     * or the specified deadline elapses.
     *
     * <p>The lock associated with this condition is atomically
     * released and the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until <em>one</em> of five things happens:
     * <ul>
     * <li>Some other thread invokes the {@link #signal} method for this
     * {@code Condition} and the current thread happens to be chosen as the
     * thread to be awakened; or
     * <li>Some other thread invokes the {@link #signalAll} method for this
     * {@code Condition}; or
     * <li>Some other thread {@linkplain Thread#interrupt interrupts} the
     * current thread, and interruption of thread suspension is supported; or
     * <li>The specified deadline elapses; or
     * <li>A &quot;<em>spurious wakeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In all cases, before this method can return the current thread must
     * re-acquire the lock associated with this condition. When the
     * thread returns it is <em>guaranteed</em> to hold this lock.
     *
     *
     * <p>If the current thread:
     * <ul>
     * <li>has its interrupted status set on entry to this method; or
     * <li>is {@linkplain Thread#interrupt interrupted} while waiting
     * and interruption of thread suspension is supported,
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared. It is not specified, in the first
     * case, whether or not the test for interruption occurs before the lock
     * is released.
     *
     *
     * <p>The return value indicates whether the deadline has elapsed,
     * which can be used as follows:
     *  <pre> {@code
     * boolean aMethod(Date deadline) {
     *   boolean stillWaiting = true;
     *   lock.lock();
     *   try {
     *     while (!conditionBeingWaitedFor()) {
     *       if (!stillWaiting)
     *         return false;
     *       stillWaiting = theCondition.awaitUntil(deadline);
     *     }
     *     // ...
     *   } finally {
     *     lock.unlock();
     *   }
     * }}</pre>
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>The current thread is assumed to hold the lock associated with this
     * {@code Condition} when this method is called.
     * It is up to the implementation to determine if this is
     * the case and if not, how to respond. Typically, an exception will be
     * thrown (such as {@link IllegalMonitorStateException}) and the
     * implementation must document that fact.
     *
     * <p>An implementation can favor responding to an interrupt over normal
     * method return in response to a signal, or over indicating the passing
     * of the specified deadline. In either case the implementation
     * must ensure that the signal is redirected to another waiting thread, if
     * there is one.
     *
     * @param deadline the absolute time to wait until
     * @return {@code false} if the deadline has elapsed upon return, else
     *         {@code true}
     * @throws InterruptedException if the current thread is interrupted
     *         (and interruption of thread suspension is supported)
     */
    boolean awaitUntil(Date deadline) throws InterruptedException;

    /**
     * Wakes up one waiting thread.
     * 唤醒一个等待中的线程。
     *
     * <p>If any threads are waiting on this condition then one
     * is selected for waking up. That thread must then re-acquire the
     * lock before returning from {@code await}.
     * 如果有任何线程正在等待这个condition，那么选择一个唤醒它。
     * 这个线程在await返回之前必须重新获取lock。
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>An implementation may (and typically does) require that the
     * current thread hold the lock associated with this {@code
     * Condition} when this method is called. Implementations must
     * document this precondition and any actions taken if the lock is
     * not held. Typically, an exception such as {@link
     * IllegalMonitorStateException} will be thrown.
     */
    void signal();

    /**
     * Wakes up all waiting threads.
     * 唤醒所有的等待中的线程。
     *
     * <p>If any threads are waiting on this condition then they are
     * all woken up. Each thread must re-acquire the lock before it can
     * return from {@code await}.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>An implementation may (and typically does) require that the
     * current thread hold the lock associated with this {@code
     * Condition} when this method is called. Implementations must
     * document this precondition and any actions taken if the lock is
     * not held. Typically, an exception such as {@link
     * IllegalMonitorStateException} will be thrown.
     */
    void signalAll();
}
