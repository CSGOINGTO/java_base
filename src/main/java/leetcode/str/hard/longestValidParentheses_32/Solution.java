package leetcode.str.hard.longestValidParentheses_32;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses("()(())"));
    }


    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] s_chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;
        for (int i = 0; i < s_chars.length; i++) {
            if (s_chars[i] == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
