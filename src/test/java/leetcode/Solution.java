package leetcode;

import org.junit.Test;

public class Solution {

    @Test
    public void test1() {
        System.out.println(f(30));
    }

    private int f(int n) {
        if (n == 1 || n == 2) return 1;
        return f(n - 1) + f(n - 2);
    }

    private int f1(int n) {
        if (n == 1 || n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
