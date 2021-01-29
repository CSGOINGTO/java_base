package leetcode.str.hard.longestValidParentheses_32;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses("()(()"));
    }


    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] s_chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        Stack<Integer> integerStack = new Stack<>();
        int maxLen = 0;
        int tmpMax = 0;
        //   )()())
        for (int i = 0; i < s_chars.length; i++) {
            if (s_chars[i] == '(') {
                stack.push(')');
            } else {
                if (stack.isEmpty() || (stack.pop() != s_chars[i])) {
                    tmpMax = 0;
                } else {
                    tmpMax += 2;
                    maxLen = Math.max(maxLen, tmpMax);
                }
            }
        }
        return maxLen;
    }
}
