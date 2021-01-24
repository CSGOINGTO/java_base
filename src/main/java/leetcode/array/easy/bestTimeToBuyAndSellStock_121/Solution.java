package leetcode.array.easy.bestTimeToBuyAndSellStock_121;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        // 记录最低的买入价格
        int buy = prices[0];
        // 记录当前最大的利润
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 当前卖出的利润
            int profit = prices[i] - buy;
            // 取的最大利润
            maxProfit = maxProfit > profit ? maxProfit : profit;
            // 取最小买入价格
            buy = buy > prices[i] ? prices[i] : buy;
        }
        return maxProfit;
    }

    public int maxProfit1(int[] prices) {
        int profit = 0;
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < prices.length;i++) {
            while(!deque.isEmpty() && deque.peekLast() > prices[i]) {
                Integer remove = deque.removeLast();
                if (!deque.isEmpty())
                    profit = Math.max(remove - deque.peekFirst(), profit);
            }
            deque.addLast(prices[i]);
        }
        if (!deque.isEmpty()) {
            profit = Math.max(deque.peekLast() - deque.peekFirst(), profit);
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length < 2) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] + dp[i][1]);
            dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
