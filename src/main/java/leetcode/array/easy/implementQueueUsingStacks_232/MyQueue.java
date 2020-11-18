package leetcode.array.easy.implementQueueUsingStacks_232;

import java.util.ArrayList;
import java.util.List;

class MyQueue {

    private List<Integer> popList;

    private List<Integer> pushList;

    private int size;

    private int popIndex;

    private int pushIndex;
import java.util.Stack;

public class MyQueue {
    private final Stack<Integer> stack_help = new Stack<>();

    private final Stack<Integer> stack_queue = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        popList = new ArrayList<>();
        pushList = new ArrayList<>();
        size = 0;
        popIndex = 0;
        pushIndex = 0;
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        pushList.add(x);
        popList.add(x);
        pushIndex++;
        size++;
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (pushIndex == popIndex) {
            return -1;
        }
        size--;
        return popList.get(popIndex++);
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (pushIndex == popIndex) {
            return -1;
        }
        return popList.get(popIndex);
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return size == 0;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
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
