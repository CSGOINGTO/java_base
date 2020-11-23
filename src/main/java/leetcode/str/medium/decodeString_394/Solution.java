package leetcode.str.medium.decodeString_394;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.decodeString3("3[a2[c]]"));
    }

    public String decodeString(String s) {
        StringBuilder resSb = new StringBuilder();
        Stack<Integer> stringNums = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        char[] s_chars = s.toCharArray();
        int num = 0;
        for (int i = 0; i < s_chars.length; i++) {
            if (s_chars[i] >= '0' && s_chars[i] <= '9') {
                for (; i < s_chars.length && (s_chars[i] >= '0' && s_chars[i] <= '9'); i++) {
                    num = num * 10 + s_chars[i] - '0';
                }
                i--;
            } else if ((s_chars[i] >= 'A' && s_chars[i] <= 'Z') || (s_chars[i] >= 'a' && s_chars[i] <= 'z')) {
                resSb.append(s_chars[i]);
            } else if (s_chars[i] == '[') {
                stringNums.push(num);
                stringStack.push(resSb.toString());
                num = 0;
                resSb = new StringBuilder();
            } else {
                int numTemp = stringNums.pop();
                String stringTemp = resSb.toString();
                if (stringTemp.length() > 0) {
                    StringBuilder sbTemp = new StringBuilder();
                    for (int j = 0; j < numTemp; j++) {
                        sbTemp.append(stringTemp);
                    }
                    resSb = new StringBuilder(stringStack.pop() + sbTemp.toString());
                }
            }
        }
        return resSb.toString();
    }

    public String decodeString1(String s) {
        Stack<String> strString = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder res = new StringBuilder();
        char[] s_chars = s.toCharArray();
        for (int i = 0; i < s_chars.length; i++) {
            if (s_chars[i] == '[') {
                strString.push(res.toString());
                res = new StringBuilder();
            } else if (s_chars[i] == ']') {
                int num = numStack.pop();
                StringBuilder temp = new StringBuilder(strString.pop());
                for (int j = 0; j < num; j++) {
                    temp.append(res);
                }
                res = temp;
            } else if (s_chars[i] >= '0' && s_chars[i] <= '9') {
                int num = 0;
                while (s_chars[i] >= '0' && s_chars[i] <= '9') {
                    num = num * 10 + s_chars[i] - '0';
                    i++;
                }
                i--;
                numStack.push(num);
            } else {
                res.append(s_chars[i]);
            }
        }
        return res.toString();
    }

    /**
     * 递归版本
     * 注意：
     * 1. 需要将当前遍历到的位置作为参数传入
     * 2. 其他操作与使用栈的解法相同
     */
    public String decodeString3(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int index) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        char[] s_chars = s.toCharArray();
        for (; index < s.length(); index++) {
            if (s_chars[index] >= '0' && s_chars[index] <= '9') {
                num = num * 10 + s_chars[index] - '0';
            } else if ((s_chars[index] >= 'a' && s_chars[index] <= 'z') || (s_chars[index] >= 'A' && s_chars[index] <= 'Z')) {
                sb.append(s_chars[index]);
            } else if (s_chars[index] == '[') {
                String[] dfs = dfs(s, ++index);
                String dfsRes = dfs[0];
                // 将index重置为已经遍历到的位置，防止重复遍历
                index = Integer.parseInt(dfs[1]);
                StringBuilder tempSb = new StringBuilder();
                while (num > 0) {
                    tempSb.append(dfsRes);
                    num--;
                }
                sb.append(tempSb.toString());
            } else {
                return new String[]{sb.toString(), String.valueOf(index)};
            }
        }
        return new String[]{sb.toString(), String.valueOf(0)};
    }

}
