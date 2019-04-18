package leetcode.array.medium.productOfArrayExceptsSelf_238;

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        if (nums.length <= 0) {
            return res;
        }
        int help = 1;
        // 左乘积
        res[0] = help;
        for (int i = 1; i < nums.length; i++) {
            res[i] = help * nums[i - 1];
            // 记录左边前一步的乘积
            help = res[i];
        }
        // 右乘积
        help = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = res[i] * help;
            // 记录右边前一步的成绩
            help = help * nums[i];
        }
        return res;
    }
}
