package leetcode.algorithm.dp.threeStepsProblemlcci_0801;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.waysToStep1(11));
    }

    public int waysToStep(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return (int) dp[n];
    }

    public int waysToStep1(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        int one = 1;
        int two = 2;
        int three = 4;
        for (int i = 4; i <= n; i++) {
            int three_temp = three;
            int two_temp = two;
            three = three_temp + one + two;
            two = three_temp;
            one = two_temp;
        }
        return three;
    }
}
