package base_knowledge.sourcecode.java.lang;

/**
 * Class {@code Object} is the root of the class hierarchy.
 * Every class has {@code Object} as a superclass. All objects,
 * including arrays, implement the methods of this class.
 * Object类是类层级中的根。
 * Object是任何一个类的超类。包括数组在内的所有的对象，都实现了该类中的方法。
 *
 * @author  unascribed
 * @see     Class
 * @since   JDK1.0
 */
public class Object {

    private static native void registerNatives();
    static {
        registerNatives();
    }

    /**
     * Returns the runtime class of this {@code Object}. The returned
     * {@code Class} object is the object that is locked by {@code
     * static synchronized} methods of the represented class.
     * 返回当前对象运行时的类。返回的Class对象表示当前对象的类，这个Class对象是被static synchronized修饰的方法锁定的对象。
     *
     * <p><b>The actual result type is {@code Class<? extends |X|>}
     * where {@code |X|} is the erasure of the static type of the
     * expression on which {@code getClass} is called.</b> For
     * example, no cast is required in this code fragment:</p>
     * 真实的结果类型是Class<? extends |X|>，其中|X|是对调用getClass表达式的静态类型的擦除。（返回的是实际子类的Class对象）
     *
     * <p>
     * {@code Number n = 0;                             }<br>
     * {@code Class<? extends Number> c = n.getClass(); }
     * 如果写成Class<Number> c = n.getClass();则编译通不过
     * </p>
     *
     * @return The {@code Class} object that represents the runtime
     *         class of this object.
     * @jls 15.8.2 Class Literals
     */
//    public final native Class<?> getClass();

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * 返回一个对象的hashcode值。这个方法是支持对于像HashMap基于hash tables的使用。
     *
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the {@code hashCode} method
     *     must consistently return the same integer, provided no information
     *     used in {@code equals} comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     *     method, then calling the {@code hashCode} method on each of
     *     the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link Object#equals(Object)}
     *     method, then calling the {@code hashCode} method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hash tables.
     * </ul>
     * hashcode的基本约定是：
     * 1. 每当在java应用程序执行期间多次对同一个对象调用hashcode方法时，只要没有修改在对象上equals方法中比较所使用的信息，
     * 该方法就必须一致地返回相同的整数。该整数不需要在应用程序的一次调用中的执行与同一个应用程序的另一次调用的执行保持一致（程序进程中无需保持一致）。
     * 2. 如果两个对象通过equals（Object）方法返回是equal的，那么这两个对象的hashCode方法返回的整数必须要相同。
     * 3. 如果两个对象通过equals方法结果不想等，hashCode方法也不必产生两个不同的整型值。然而，程序员需要知道对于equals不同的两个对象，hashCode产生不同的整型值可能会提升哈希表的性能。
     *
     *
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     * 为了尽可能的实用，Object定义的hashCode方法，对于不同的对象返回的是不同的整数值。（这是典型的实现 ：通过将对象的内存地址转化为整型，但是在Java代码中不必去实现它
     *
     * @return  a hash code value for this object.
     * @see     Object#equals(Object)
     * @see     System#identityHashCode
     */
    public native int hashCode();

    /**
     * Indicates whether some other object is "equal to" this one.
     * 指示其他对象是否等于此对象。
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * equals方法实现了对非空对象引用的等价关系。
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * equals方法具有自反性：对于任何非空引用x,x.equals(x)应该返回true
     * equals方法具有对称性：对于任何非空引用x和y；x.equals(y)返回true，那么y.equals(x)也返回true
     * equals方法具有传递性：对于任何非空引用x，y和z；x.equals(y)返回true；y.equals(z)也返回true，那么x.equals(z)也返回true
     * equals方法具有一致性：对于任何非空引用x和y，倘若没有任何用于equals方法比较的信息被修改，多次调用x.equals(y)应该一直返回true或者一直返回false
     * 对于任何非空引用x，x.equals(null)一定返回false
     *
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     * Object的equals方法实现了对象之间最有辨别力的可能的等价关系，也就是说，对于任何非空对象x和y当且仅当x和y引用同一个对象时这个方法返回ture(x == y 值为 true)
     *
     * 需要注意的是：无论何时equals方法被重写时，重写hashCode方法很重要。这样以便维持普遍约定：相等的对象必须有相等的哈希码
     *
     * @param   obj   the reference object with which to compare.
     * @return  {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     * @see     #hashCode()
     * @see     java.util.HashMap
     */
    public boolean equals(Object obj) {
        return (this == obj);
    }

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * will be true, and that the expression:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * will be {@code true}, this is not an absolute requirement.
     * 创建并返回此对象的副本,“副本”的确切含义取决于这个对象的类。
     * 普遍的目的是，对于任何一个对象x，表达式 x.clone() != x 为true，x.clone().getClass() == x.getClass() 也为 true，但这些都不是绝对的要求。
     * 而有一个典型的情况：x.clone().equals(x) 为 true 这也不是绝对的要求。
     *
     * <p>
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}.  If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     * <p>
     * By convention, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by {@code super.clone} before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by {@code super.clone}
     * need to be modified.
     * <p>
     * The method {@code clone} for class {@code Object} performs a
     * specific cloning operation. First, if the class of this object does
     * not implement the interface {@code Cloneable}, then a
     * {@code CloneNotSupportedException} is thrown. Note that all arrays
     * are considered to implement the interface {@code Cloneable} and that
     * the return type of the {@code clone} method of an array type {@code T[]}
     * is {@code T[]} where T is any reference or primitive type.
     * Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of
     * the corresponding fields of this object, as if by assignment; the
     * contents of the fields are not themselves cloned. Thus, this method
     * performs a "shallow copy" of this object, not a "deep copy" operation.
     * <p>
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     * 按照惯例，应该通过调用 super.clone 来获得返回的对象。如果一个类和它所有的父类（除了Object）遵守这个惯例,这个情况应该是 x.clone().getClass() == x.getClass() 为 true。
     * 依照惯例，clone方法返回的对象应该独立于这个对象（被clone的对象）。
     * 为了实现这个独立性，在返回之前，修改super.clone返回的对象的一个或多个字段是有必要的。
     * 通常，这意味着复制包含被克隆对象的内部“深层结构”的任何可变对象，并用对副本的引用替换对这些对象的引用。
     * 如果一个类仅包含基本字段或对不可变对象的引用，那么通常是 super.clone 返回的对象中没有需要修改字段的情况。
     *
     * Object类的克隆方法表现为一个特殊的克隆操作
     * 首先，如果一个对象没有实现Cloneable接口，那么clone方法会抛出CloneNotSupportedException异常。
     * 注意所有的数组都实现了Cloneable接口，并且数组 T[] 的clone方法返回值类型是数组 T[] , T是任何引用类型或基本类型。
     * 此外，这个方法创建了一个这个对象（被clone的对象）的类的新实例，并且它所有字段的确切内容都与这个被克隆的对象一致。
     * 因此这个方法表现为对象的“浅拷贝”，而不是“深拷贝”。
     *
     * Object类没有实现Cloneable接口,所以调用Object类的实例的clone方法会抛出运行时异常。
     *
     *
     * @return     a clone of this instance.
     * @throws  CloneNotSupportedException  if the object's class does not
     *               support the {@code Cloneable} interface. Subclasses
     *               that override the {@code clone} method can also
     *               throw this exception to indicate that an instance cannot
     *               be cloned.
     * @see Cloneable
     */
    protected native Object clone() throws CloneNotSupportedException;

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * 返回一个代表这个对象的字符串。一般来说，toString方法返回这个对象的“文本表示”。结果应该简明扼要，内容翔实，便于人们阅读。
     * 建议所有的子类重写这个方法。
     *
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     * 对于Object的toString方法，返回一个由该对象的类名，“@”符号和这个对象的哈希码的16进制构成。
     * 换句话说，toString方法返回的值为：getClass().getName() + '@' + Integer.toHexString(hashCode)
     *
     * @return  a string representation of the object.
     */
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * Wakes up a single thread that is waiting on this object's
     * monitor. If any threads are waiting on this object, one of them
     * is chosen to be awakened. The choice is arbitrary and occurs at
     * the discretion of the implementation. A thread waits on an object's
     * monitor by calling one of the {@code wait} methods.
     * 唤醒在该对象监视器上等待的单个线程。如果有任何线程在等待该对象，则选择其中一个被唤醒。
     * 选择是任意的，由实现自行决定的。线程通过调用wait方法，在一个对象的监视器上等待。
     * <p>
     * The awakened thread will not be able to proceed until the current
     * thread relinquishes the lock on this object. The awakened thread will
     * compete in the usual manner with any other threads that might be
     * actively competing to synchronize on this object; for example, the
     * awakened thread enjoys no reliable privilege or disadvantage in being
     * the next thread to lock this object.
     * <p>
     * 被唤醒的线程将无法继续执行，直到当前线程放弃该对象上的锁。
     * 被唤醒的线程将以通常的方式与其他可能在此对象上积极竞争synchronize的线程竞争；
     * 例如，被唤醒的线程在成为下一个锁定该对象的线程方面没有可靠的特权或缺点。（只是被唤醒了而已，还是需要和其他线程竞争synchronize）
     *
     *
     * This method should only be called by a thread that is the owner
     * of this object's monitor. A thread becomes the owner of the
     * object's monitor in one of three ways:
     * 该方法只能由拥有这个对象监视器的线程调用，一个线程称为对象监视器的拥有者有三种方式：
     * 1. 通过执行该对象的同步方法
     * 2. 通过执行锁定该对象的同步代码块
     * 3. 对于Class类的对象，通过执行该类的静态同步方法。
     * <ul>
     * <li>By executing a synchronized instance method of that object.
     * <li>By executing the body of a {@code synchronized} statement
     *     that synchronizes on the object.
     * <li>For objects of type {@code Class,} by executing a
     *     synchronized static method of that class.
     * </ul>
     * <p>
     * Only one thread at a time can own an object's monitor.
     * 在一段时间只有一个线程拥有对象监视器
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     *               如果当前线程不是该对象的拥有者，则抛出IllegalMonitorStateException
     * @see        Object#notifyAll()
     * @see        Object#wait()
     */
//    public final native void notify();

    /**
     * Wakes up all threads that are waiting on this object's monitor. A
     * thread waits on an object's monitor by calling one of the
     * {@code wait} methods.
     * 唤醒所有等待该对象监视器的线程。一个线程通过调用wait方法等待对象监视器。
     * <p>
     * The awakened threads will not be able to proceed until the current
     * thread relinquishes the lock on this object. The awakened threads
     * will compete in the usual manner with any other threads that might
     * be actively competing to synchronize on this object; for example,
     * the awakened threads enjoy no reliable privilege or disadvantage in
     * being the next thread to lock this object.
     * <p>
     * 被唤醒的线程将无法执行，直到当前线程释放对象上的锁。
     * 被唤醒的线程将以通常的方式与其他可能在此对象上积极竞争synchronize的线程竞争；
     * 例如，被唤醒的线程在成为下一个锁定该对象的线程方面没有可靠的特权或缺点。（只是被唤醒了而已，还是需要和其他线程竞争synchronize）
     *
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     * 这个方法只能由拥有这个对象监视器的线程调用，可以看notify方法了解一个线程如何成为对象监视器的拥有者。
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     *               如果当前线程不是该对象的拥有者，则抛出IllegalMonitorStateException
     * @see        Object#notify()
     * @see        Object#wait()
     */
//    public final native void notifyAll();

    /**
     * Causes the current thread to wait until either another thread invokes the
     * {@link Object#notify()} method or the
     * {@link Object#notifyAll()} method for this object, or a
     * specified amount of time has elapsed.
     * 导致当前线程等待，直到其他线程调用了这个对象的notify，notifyAll方法，或指定的时间过去。
     * <p>
     * The current thread must own this object's monitor.
     * <p>
     * 当前线程必须拥有该对象的监视器。
     *
     * This method causes the current thread (call it <var>T</var>) to
     * place itself in the wait set for this object and then to relinquish
     * any and all synchronization claims on this object. Thread <var>T</var>
     * becomes disabled for thread scheduling purposes and lies dormant
     * until one of four things happens:
     * 这个方法导致当前线程（称之为T）将自己放入该对象的等待集合（wait set），然后放弃该对象上的任何和所有同步声明。
     * 为了线程调度的目的，线程T被禁用，处于休眠状态，直到发生以下四件事之一：
     *
     * <ul>
     * <li>Some other thread invokes the {@code notify} method for this
     * object and thread <var>T</var> happens to be arbitrarily chosen as
     * the thread to be awakened.
     * 有其他线程调用了该对象的notify方法，并且线程T被随机选择为被唤醒的线程；
     *
     * <li>Some other thread invokes the {@code notifyAll} method for this
     * object.
     * 有其他线程调用了该对象的notifyAll方法；
     *
     * <li>Some other thread {@linkplain Thread#interrupt() interrupts}
     * thread <var>T</var>.
     * 其他线程调用了这个线程的interrupt方法。
     *
     * <li>The specified amount of real time has elapsed, more or less.  If
     * {@code timeout} is zero, however, then real time is not taken into
     * consideration and the thread simply waits until notified.
     * 指定的时间到了。如果timeout是0，则真正的时间将不被考虑，并且线程等待直到被唤醒。（相当于wait()方法）
     *
     * </ul>
     * The thread <var>T</var> is then removed from the wait set for this
     * object and re-enabled for thread scheduling. It then competes in the
     * usual manner with other threads for the right to synchronize on the
     * object; once it has gained control of the object, all its
     * synchronization claims on the object are restored to the status quo
     * ante - that is, to the situation as of the time that the {@code wait}
     * method was invoked. Thread <var>T</var> then returns from the
     * invocation of the {@code wait} method. Thus, on return from the
     * {@code wait} method, the synchronization state of the object and of
     * thread {@code T} is exactly as it was when the {@code wait} method
     * was invoked.
     * 然后将线程T从该对象的等待集合（wait set）中删除，并为线程调度重新启用。
     * 然后，它以通常的方式与其他线程竞争该对象上的同步权；一旦它获得了对对象的控制，它对对象的所有同步声明都恢复到之前的状态--也就是说，恢复到调用wait方法时的状态。
     * 然后，线程T从wait方法的调用返回。因此，从等待方法返回时，对象和线程T的同步状态与调用等待方法时完全相同。
     *
     * <p>
     * A thread can also wake up without being notified, interrupted, or
     * timing out, a so-called <i>spurious wakeup</i>.  While this will rarely
     * occur in practice, applications must guard against it by testing for
     * the condition that should have caused the thread to be awakened, and
     * continuing to wait if the condition is not satisfied.  In other words,
     * waits should always occur in loops, like this one:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait(timeout);
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * (For more information on this topic, see Section 3.2.3 in Doug Lea's
     * "Concurrent Programming in Java (Second Edition)" (Addison-Wesley,
     * 2000), or Item 50 in Joshua Bloch's "Effective Java Programming
     * Language Guide" (Addison-Wesley, 2001).
     * 一个线程也可以自己醒来而不需要被唤醒，被打断或等待时间结束，这种情况被称为伪唤醒。
     * 尽管这个很少在实际中发生，但应用程序必须通过测试导致线程被唤醒的条件来防止这种情况发生，如果条件不满足，则继续等待。
     * 换句话说，等待应该总是出现在循环中，就像下面这样：
     * synchronized (obj) {
     *      while(condition does not hold)
     *          obj.wait(timeout);
     *      ...
     * }
     *
     *
     * <p>If the current thread is {@linkplain Thread#interrupt()
     * interrupted} by any thread before or while it is waiting, then an
     * {@code InterruptedException} is thrown.  This exception is not
     * thrown until the lock status of this object has been restored as
     * described above.
     * 如果当前线程在等待之前或等待期间被任意线程中断，则抛出InterruptedException。
     * 在如上所述恢复此对象的锁状态之前，则不会抛出此异常。
     *
     * <p>
     * Note that the {@code wait} method, as it places the current thread
     * into the wait set for this object, unlocks only this object; any
     * other objects on which the current thread may be synchronized remain
     * locked while the thread waits.
     * 注意wait方法，它将当前线程放入这个对象的等待集合，并释放这个对象锁；在线程等待时，当前线程上可能被同步的任何其他对象将保持锁定。
     *
     * <p>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     * 这个方法应该只在拥有这个对象的监视器的线程中调用。可以看notify方法了解一个线程如何成为对象监视器的拥有者。
     *
     * @param      timeout   the maximum time to wait in milliseconds.
     * @throws  IllegalArgumentException      if the value of timeout is
     *               negative.
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of the object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        Object#notify()
     * @see        Object#notifyAll()
     */
//    public final native void wait(long timeout) throws InterruptedException;

    /**
     * Causes the current thread to wait until another thread invokes the
     * {@link Object#notify()} method or the
     * {@link Object#notifyAll()} method for this object, or
     * some other thread interrupts the current thread, or a certain
     * amount of real time has elapsed.
     * <p>
     * This method is similar to the {@code wait} method of one
     * argument, but it allows finer control over the amount of time to
     * wait for a notification before giving up. The amount of real time,
     * measured in nanoseconds, is given by:
     * <blockquote>
     * <pre>
     * 1000000*timeout+nanos</pre></blockquote>
     * <p>
     * In all other respects, this method does the same thing as the
     * method {@link #wait(long)} of one argument. In particular,
     * {@code wait(0, 0)} means the same thing as {@code wait(0)}.
     * <p>
     * The current thread must own this object's monitor. The thread
     * releases ownership of this monitor and waits until either of the
     * following two conditions has occurred:
     * <ul>
     * <li>Another thread notifies threads waiting on this object's monitor
     *     to wake up either through a call to the {@code notify} method
     *     or the {@code notifyAll} method.
     * <li>The timeout period, specified by {@code timeout}
     *     milliseconds plus {@code nanos} nanoseconds arguments, has
     *     elapsed.
     * </ul>
     * <p>
     * The thread then waits until it can re-obtain ownership of the
     * monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait(timeout, nanos);
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @param      timeout   the maximum time to wait in milliseconds.
     * @param      nanos      additional time, in nanoseconds range
     *                       0-999999.
     * @throws  IllegalArgumentException      if the value of timeout is
     *                      negative or the value of nanos is
     *                      not in the range 0-999999.
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     */
//    public final void wait(long timeout, int nanos) throws InterruptedException {
//        if (timeout < 0) {
//            throw new IllegalArgumentException("timeout value is negative");
//        }
//
//        if (nanos < 0 || nanos > 999999) {
//            throw new IllegalArgumentException(
//                                "nanosecond timeout value out of range");
//        }
//
//        if (nanos > 0) {
//            timeout++;
//        }
//
//        wait(timeout);
//    }

    /**
     * Causes the current thread to wait until another thread invokes the
     * {@link Object#notify()} method or the
     * {@link Object#notifyAll()} method for this object.
     * In other words, this method behaves exactly as if it simply
     * performs the call {@code wait(0)}.
     * <p>
     * The current thread must own this object's monitor. The thread
     * releases ownership of this monitor and waits until another thread
     * notifies threads waiting on this object's monitor to wake up
     * either through a call to the {@code notify} method or the
     * {@code notifyAll} method. The thread then waits until it can
     * re-obtain ownership of the monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait();
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of the object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        Object#notify()
     * @see        Object#notifyAll()
     */
//    public final void wait() throws InterruptedException {
//        wait(0);
//    }

    /**
     * Called by the garbage collector on an object when garbage collection
     * determines that there are no more references to the object.
     * A subclass overrides the {@code finalize} method to dispose of
     * system resources or to perform other cleanup.
     * 当垃圾收集确定没有对对象的更多引用时，由垃圾收集器在对象上调用该方法。
     * 子类重写finalize方法以释放系统资源或执行其他清理操作。
     *
     * <p>
     * The general contract of {@code finalize} is that it is invoked
     * if and when the Java&trade; virtual
     * machine has determined that there is no longer any
     * means by which this object can be accessed by any thread that has
     * not yet died, except as a result of an action taken by the
     * finalization of some other object or class which is ready to be
     * finalized. The {@code finalize} method may take any action, including
     * making this object available again to other threads; the usual purpose
     * of {@code finalize}, however, is to perform cleanup actions before
     * the object is irrevocably discarded. For example, the finalize method
     * for an object that represents an input/output connection might perform
     * explicit I/O transactions to break the connection before the object is
     * permanently discarded.
     * finalize的一般约定是，当Java™虚拟机已经确定，没有任何方法可以让任何还没有死亡的线程访问这个对象，除非是由于某个准备结束的其他对象或类的终结所采取的操作。
     * finalize方法可以采取任何操作，包括让这个对象再次对其他线程可用;然而，finalize的通常目的是在不可撤销地丢弃对象之前执行清理操作。
     * 例如，表示I/O连接的对象的finalize方法可能会执行显式的I/O事务，以在永久丢弃对象之前中断连接。
     *
     * <p>
     * The {@code finalize} method of class {@code Object} performs no
     * special action; it simply returns normally. Subclasses of
     * {@code Object} may override this definition.
     * 类对象的finalize方法不执行任何特殊操作;它只是正常返回。Object的子类可以重写此定义。
     *
     * <p>
     * The Java programming language does not guarantee which thread will
     * invoke the {@code finalize} method for any given object. It is
     * guaranteed, however, that the thread that invokes finalize will not
     * be holding any user-visible synchronization locks when finalize is
     * invoked. If an uncaught exception is thrown by the finalize method,
     * the exception is ignored and finalization of that object terminates.
     * Java编程语言不保证哪个线程将为任何给定对象调用finalize方法。
     * 但是，可以保证调用finalize的线程在调用finalize时不会持有任何用户可见的同步锁。
     * 如果finalize方法抛出未捕获的异常，则忽略该异常并终止该对象的终结。
     *
     * <p>
     * After the {@code finalize} method has been invoked for an object, no
     * further action is taken until the Java virtual machine has again
     * determined that there is no longer any means by which this object can
     * be accessed by any thread that has not yet died, including possible
     * actions by other objects or classes which are ready to be finalized,
     * at which point the object may be discarded.
     * 在为对象调用finalize方法之后，不会采取进一步的操作，直到Java虚拟机再次确定没有任何方法可以让任何尚未死亡的线程访问该对象，
     * 包括其他准备结束的对象或类可能采取的操作，此时该对象可能会被丢弃。
     *
     * <p>
     * The {@code finalize} method is never invoked more than once by a Java
     * virtual machine for any given object.
     * 对于任何给定的对象，finalize方法不会被Java虚拟机调用一次以上。
     *
     * <p>
     * Any exception thrown by the {@code finalize} method causes
     * the finalization of this object to be halted, but is otherwise
     * ignored.
     * 任何被finalize方法抛出的异常导致对象的终结被终端，但是异常被忽略。
     *
     * @throws Throwable the {@code Exception} raised by this method
     * @see java.lang.ref.WeakReference
     * @see java.lang.ref.PhantomReference
     * @jls 12.6 Finalization of Class Instances
     */
    protected void finalize() throws Throwable { }
}
