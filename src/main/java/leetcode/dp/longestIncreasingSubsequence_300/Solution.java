package leetcode.dp.longestIncreasingSubsequence_300;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int tmp = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) tmp = Math.max(tmp, dp[j] + 1);
            }
            dp[i] = tmp;
            ans = Math.max(ans, tmp);
        }
        return ans;
    }
}
