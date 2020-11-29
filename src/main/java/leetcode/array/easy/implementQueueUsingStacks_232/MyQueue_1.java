package leetcode.array.easy.implementQueueUsingStacks_232;

import java.util.Stack;

public class MyQueue_1 {

    private final Stack<Integer> stack_help = new Stack<>();

    private final Stack<Integer> stack_queue = new Stack<>();

    public MyQueue_1() {
    }

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
