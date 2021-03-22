package leetcode.algorithm.dp.maxValue;

public class Solution {

    // 工作时间安排
    private int[][] taskTime;

    // 上一个可做工作
    private int[] prev;

    // 工资
    private int[] salary;

    public Solution(int[][] taskTime, int[] salary) {
        this.taskTime = taskTime;
        this.prev = new int[salary.length];
        this.salary = salary;

        prev[0] = -1;
        for (int i = 0; i < prev.length; i++) {
            for (int n = i; n > 0; n--) {
                if (taskTime[i][0] >= taskTime[n - 1][1]) {
                    prev[i] = n - 1;
                    break;
                } else {
                    prev[i] = -1;
                }
            }
        }
    }

    private int opt(int index, int[] dp) {
        if (index > -1) {
            if (dp[index] != 0) return dp[index];
            int way1 = opt(prev[index], dp) + salary[index];
            int way2 = opt(index - 1, dp);
            int max = Math.max(way1, way2);
            dp[index] = max;
            return max;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] taskTime = new int[][]{{1, 4}, {3, 5}, {0, 6}, {4, 7}, {3, 8}, {5, 9}, {6, 10}, {8, 11}};
        int[] salary = new int[]{5, 1, 8, 4, 6, 3, 2, 4};
        Solution solution = new Solution(taskTime, salary);
        int[] dp = new int[salary.length];
        int max = solution.opt(7, dp);
        System.out.println(max);
    }
}
