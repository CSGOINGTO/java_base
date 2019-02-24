package leetcode.array.easy.bestTimeToBuyAndSellStock_121;

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
}
