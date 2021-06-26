package leetcode.str.medium.longestPalindromicSubstring_5;

public class Solution {

    /**
     * 解法一：通过迭代寻找最长的回文串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) return s;
        int left;
        int right;
        char[] s_chars = s.toCharArray();
        int maxLength = 0;
        int[] strArea = new int[2];
        for (left = 0; left < s.length(); left++) {
            int start = left;
            right = s.length() - 1;
            int count = 0;
            while (left < right) {
                int end = right;
                if (s_chars[left] == s_chars[right]) {
                    count++;
                    if (left + 1 == right) {
                        if (maxLength < (end + count - start)) {
                            maxLength = end + count - start;
                            strArea[0] = start;
                            strArea[1] = end + count;
                        }
                        break;
                    }
                    left++;
                    right--;
                    if (left == right) {
                        if (maxLength < (end + count - start)) {
                            maxLength = end + count - start;
                            strArea[0] = start;
                            strArea[1] = end + count;
                        }
                        break;
                    }
                    continue;
                }
                right += (count - 1);
                left -= count;
                count = 0;
            }
            left = start;
        }
        if (maxLength != 0) {
            return s.substring(strArea[0], strArea[1]);
        }
        return s.substring(0, 1);
    }

    /**
     * 解法二：DP解法（通过标记当前位置是否是回文串，并通过寻找上个位置是否是回文串来辅助判断）
     */
    public String longestPalindrome1(String s) {
        if(s == null || s.length() == 0) return s;
        boolean[][] isPalindromes = new boolean[s.length()][s.length()];
        int max = 0;
        String res = null;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (i == j) {
                    isPalindromes[i][j] = true;
                    if (max == 0) res = s.substring(i, j + 1);
                    continue;
                }
                if (s.charAt(i) == s.charAt(j) && (isPalindromes[i + 1][j - 1] || i + 1 == j)) {
                    isPalindromes[i][j] = true;
                    if (max < j - i) {
                        max = j - i;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }
}
