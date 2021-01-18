package leetcode.algorithm.dp.opt;

public class Solution {

    public int rec_opt(int[] arr, int i) {
        if (i == 0) return 0;
        else if (i == 1) return Math.max(arr[0], arr[1]);
        else {
            int a = rec_opt(arr, i - 2) + arr[i];
            int b = rec_opt(arr, i - 1);
            return Math.max(a, b);
        }
    }

    public int dp_opt(int[] arr) {
        // 动态规划数组，保存已遍历到的元素的结果
        int[] opt = new int[arr.length];
        // 初始化动态规划数组
        opt[0] = arr[0];
        opt[1] = Math.max(arr[0], arr[1]);
        // 通过dp公式得出当前元素结果
        for (int i = 2; i < opt.length; i++) {
            int a = opt[i - 2] + arr[i];
            int b = opt[i - 1];
            opt[i] = Math.max(a, b);
        }
        return opt[arr.length - 1];
    }

}
