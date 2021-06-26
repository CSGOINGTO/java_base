package leetcode.design.medium.designAStackWithIncrementOperation_1381;

public class CustomStack {

    public static void main(String[] args) {
        CustomStack stack = new CustomStack(3);
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.increment(5, 100);
        stack.increment(2, 100);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    private Integer[] stack;

    private int currentSize;

    public CustomStack(int maxSize) {
        stack = new Integer[maxSize];
        currentSize = 0;
    }

    public void push(int x) {
        if (currentSize < stack.length) {
            stack[currentSize++] = x;
        }
    }

    public int pop() {
        if (currentSize > 0) {
            int res = stack[currentSize - 1];
            stack[currentSize - 1] = null;
            currentSize--;
            return res;
        }
        return -1;
    }

    public void increment(int k, int val) {
        int inc = Math.min(k, currentSize);
        for (int i = 0; i < inc; i++) {
            stack[i] = stack[i] + val;
        }
    }

}
