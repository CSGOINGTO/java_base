package leetcode.str.medium.decodeString_394;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.decodeString1("abc3[cd]xyz"));
    }

    public String decodeString(String s) {
        StringBuilder resStr = new StringBuilder();
        String midStr = "";
        Stack<Integer> stringNums = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        char[] s_chars = s.toCharArray();
        int num = 0;
        for (int i = 0; i < s_chars.length; i++) {
            if (s_chars[i] >= '0' && s_chars[i] <= '9') {
                num = num * 10 + s_chars[i] - '0';
            } else if (s_chars[i] == '[') {
                if (!"".equals(midStr)) {
                    stringStack.push(midStr);
                }
                stringNums.push(num);
                midStr = "";
                num = 0;
            } else if ((s_chars[i] >= 'A' && s_chars[i] <= 'Z') || (s_chars[i] >= 'a' && s_chars[i] <= 'z')) {
                midStr += s_chars[i];
            } else if (s_chars[i] == ']') {
                if (!"".equals(midStr)) {
                    int nums = stringNums.pop();
                    String temp = midStr;
                    for (int j = 0; j < nums - 1; j++) {
                        midStr += temp;
                    }
                    if (!stringStack.empty()) {
                        stringStack.push(stringStack.pop() + midStr);
                    }
                    if (stringNums.empty() && stringStack.empty()) {
                        resStr.append(midStr);
                    } else if (stringNums.empty() && !stringStack.empty()) {
                        resStr.append(stringStack.pop());
                    }
                    midStr = "";
                } else {
                    String tempString = stringStack.pop();
                    String temp = "";
                    int nums = stringNums.pop();
                    for (int j = 0; j < nums; j++) {
                        temp += tempString;
                    }
                    if (!stringStack.empty()) {
                        stringStack.push(stringStack.pop() + temp);
                    }
                    if (stringNums.empty()) {
                        resStr.append(temp);
                    }
                }
            }
        }
        String res = resStr.toString();
//        while (!stringStack.isEmpty()) {
//            String temp = stringStack.pop();
//            if (!"".equals(temp)) {
//                res += temp;
//            }
//        }
        return res;
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
