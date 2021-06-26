package leetcode.str.easy.implementStrStr_28;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.strStr("aaaa", "aaaaa"));
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        char[] haystack_chars = haystack.toCharArray();
        char[] needle_chars = needle.toCharArray();
        for (int i = 0; i < haystack.length(); i++) {
            int len = 0;
            if (haystack_chars[i] == needle_chars[len]) {
                for (; len < needle.length() && (i + len) < haystack.length(); len++) {
                    if (haystack_chars[i + len] != needle_chars[len]) {
                        break;
                    }
                }
                if (len == needle.length())
                    return i;
            }
        }
        return -1;
    }
}
