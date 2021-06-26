### 思路一
+ 使用两个List分别代表push后的队列和pop后的队列，使用pushIndex表示push后队列的位置；使用popIndex表示pop后队列的位置，使用size表示队列中元素的个数
+ 每次push时，pushIndex+1，size+1
+ 每次pop时，popIndex+1，size-1

### 代码
```java
class MyQueue {

    private List<Integer> popList;

    private List<Integer> pushList;

    private int size;

    private int popIndex;

    private int pushIndex;

    /** Initialize your data structure here. */
    public MyQueue() {
        popList = new ArrayList<>();
        pushList = new ArrayList<>();
        size = 0;
        popIndex = 0;
        pushIndex = 0;
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        pushList.add(x);
        popList.add(x);
        pushIndex++;
        size++;
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (pushIndex == popIndex) {
            return -1;
        }
        size--;
        return popList.get(popIndex++);
    }

    /** Get the front element. */
    public int peek() {
        if (pushIndex == popIndex) {
            return -1;
        }
        return popList.get(popIndex);
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return size == 0;
    }
}
```

### 复杂度
+ 时间复杂度：O(1)
+ 空间复杂度：O(n)

***
***
***

### 思路二

---
- stack_help： 1->2->3->4 ... empty ... 5->6
- stack_queue：4->3->2->1 ... empty ... 6->5
---

+ 使用栈stack_help存储每次push进来的元素，使用另外一个栈stack_queue模拟队列的操作
+ 每次push元素时都进入到stack_help
+ 每次pop元素时，首先判断stack_queue中是否存在元素，如果不存在元素，则直接将stack_help中的元素全部pop出来放入到stack_queue中，然后再从stack_queue中pop；
否则直接从stack_help中pop
+ 每次peek元素时，原理同pop操作，只是将最后的pop修改为peek。


```java
public class MyQueue {
    private final Stack<Integer> stack_help = new Stack<>();

    private final Stack<Integer> stack_queue = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stack_help.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (stack_queue.isEmpty()) {
            while (!stack_help.isEmpty()) {
                stack_queue.push(stack_help.pop());
            }
        }
        return stack_queue.pop();

    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (stack_queue.isEmpty()) {
            while (!stack_help.isEmpty()) {
                stack_queue.push(stack_help.pop());
            }
        }
        return stack_queue.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack_help.isEmpty() && stack_queue.isEmpty();
    }

}
```
### 复杂度
+ 时间复杂度：
    + push()：O(1)
    + pop()：O(n)
    + peek()：O(n)
+ 空间复杂度
    + push()：O(n)
    + pop()：O(n)
    + peek()：O(n)
