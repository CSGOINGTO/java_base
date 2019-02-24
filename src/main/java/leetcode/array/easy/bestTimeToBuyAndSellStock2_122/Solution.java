package leetcode.array.easy.bestTimeToBuyAndSellStock2_122;

public class Solution {

    /**
     * 解法一：典型的贪心策略：只要第二天的价格比第一天的价格要高，就卖出。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    /**
     * 解法二：自己想的。。。
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int profit = 0;
        int buy = Integer.MAX_VALUE;
        int sell = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            buy = Math.min(buy, prices[i]);
            sell = Math.max(sell, prices[i]);
            profit += sell - buy;
            if (sell > buy) {
                buy = prices[i];
            }
            sell = Integer.MIN_VALUE;
        }
        return profit;
    }
}
