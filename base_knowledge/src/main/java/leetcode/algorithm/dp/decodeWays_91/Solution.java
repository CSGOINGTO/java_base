package leetcode.algorithm.dp.decodeWays_91;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("213101278912"));
    }

    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == '0') return 0;
        int[] dp = new int[s.length() + 1];//长度为n时，的排列组合种类
        dp[0] = 1;
        dp[1] = 1;
        if (s.length() == 1) return dp[1];
        for (int i = 2; i <= s.length(); i++) {






            int num = Integer.parseInt(String.valueOf(s.charAt(i - 1)));//得到当前数；
            int nums2 = Integer.parseInt(String.valueOf(s.charAt(i - 2)));//得到当前数的前一个数
            if (nums2 + num == 0 || (num == 0 && nums2 > 2)) {
                return 0;
            } else if (num == 0 || nums2 == 0) {
                dp[i] = num == 0 ? dp[i - 2] : dp[i - 1];
            } else {
                dp[i] = nums2 * 10 + num > 26 ? dp[i - 1] : dp[i - 2] + dp[i - 1];
            }
        }
        return dp[s.length()];
    }
}
