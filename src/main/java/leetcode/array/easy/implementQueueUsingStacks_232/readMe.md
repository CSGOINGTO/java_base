### 思路
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