package leetcode.str.medium.decodeString_394;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.decodeString1("3[a]2[b4[F]c]"));
    }

    public String decodeString(String s) {
        char[] s_chars = s.toCharArray();
        Stack<String> stringStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int matchNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s_chars[i] > '0' && s_chars[i] <= '9') {
                int num = s_chars[i] - '0';
                while (s_chars[++i] >= '0' && s_chars[i] <= '9') {
                    num = num * 10 + s_chars[i] - '0';
                }
                i--;
                numStack.push(num);
            }
            if ((s_chars[i] >= 'a' && s_chars[i] <= 'z') || (s_chars[i] >= 'A' && s_chars[i] <= 'Z')) {
                sb.append(s_chars[i]);
                while (i++ < s.length() && (s_chars[i] >= 'a' && s_chars[i] <= 'z') || (s_chars[i] >= 'A' && s_chars[i] <= 'Z')) {
                    sb.append(s_chars[i]);
                }
                i--;
                if (matchNum > 1) {
                    stringStack.push(stringStack.pop() + sb.toString());
                } else {
                    stringStack.push(sb.toString());
                }
                sb = new StringBuilder();
            }
            if (s_chars[i] == '[') {
                matchNum++;
            }
            if (s_chars[i] == ']') {
                if (!numStack.isEmpty() && !stringStack.isEmpty()) {
                    matchNum--;
                    String temp = stringStack.pop();
                    int num = numStack.pop();
                    for (int j = 0; j < num; j++) {
                        sb.append(temp);
                    }
                    if (!stringStack.isEmpty()) {
                        stringStack.push(stringStack.pop() + sb.toString());
                    } else {
                        stringStack.push(sb.toString());
                    }
                    sb = new StringBuilder();
                }
//                if (!sb.toString().equals("")) {
//                    String temp = sb.toString();
//                    int num = numStack.pop();
//                    for (int j = 1; j < num; j++) {
//                        sb.append(temp);
//                    }
//                    if (!stringStack.isEmpty()) {
//                        stringStack.push(stringStack.pop() + sb.toString());
//                    } else {
//                        stringStack.push(sb.toString());
//                    }
//                    sb = new StringBuilder();
//                }
            }
        }
        if (!numStack.isEmpty()) {
            int num = numStack.pop();
            StringBuilder sbTemp = new StringBuilder();
            String temp = stringStack.pop();
            for (int i = 0; i < num; i++) {
                sbTemp.append(temp);
            }
            return sbTemp.append(sb.toString()).toString();
        }
        if (!stringStack.isEmpty()) {
            return stringStack.pop() + sb.toString();
        }
        return sb.toString();
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

}
