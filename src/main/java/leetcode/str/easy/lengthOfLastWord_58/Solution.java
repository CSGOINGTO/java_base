package leetcode.str.easy.lengthOfLastWord_58;

public class Solution {

    /**
     * 方法一：遍历字符串，通过判断空格所在的位置来获得最后一个单词的长度
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = 0;
        int lastLen = 0;
        boolean isLast = false;
        for (int i = 0; i < s.length(); i++) {
            len++;
            if (s.charAt(i) == ' ') {
                if (len != 1) lastLen = len - 1;
                len = 0;
                if (i == s.length() - 1) isLast = true;
            }
        }
        return isLast ? lastLen : len;
    }

    /**
     * 方法二：通过寻找最后一个空格和倒数第二个空格，获取最后一个单词的长度
     */
    public int lengthOfLastWord1(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') end--;
        if (end < 0) return 0;
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') start--;
        return end - start;
    }
}
