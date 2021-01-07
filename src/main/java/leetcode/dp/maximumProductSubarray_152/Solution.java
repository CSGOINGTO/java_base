package leetcode.dp.maximumProductSubarray_152;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(new int[]{-1, -2, -9, -6}));
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], Math.max(nums[1], nums[0] * nums[1]));
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = dpMin[0] = nums[0];
        dpMax[1] = Math.max(nums[1], dpMax[0] * nums[1]);
        dpMin[1] = Math.min(nums[1], dpMin[0] * nums[1]);
        int max = Math.max(dpMax[0], dpMax[1]);
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > 0) {
                dpMax[i] = Math.max(dpMax[i - 1] * nums[i], nums[i]);
                dpMin[i] = Math.min(dpMin[i - 1] * nums[i], nums[i]);
            } else {
                dpMax[i] = Math.max(dpMin[i - 1] * nums[i], nums[i]);
                dpMin[i] = Math.min(dpMax[i - 1] * nums[i], nums[i]);
            }
            max = Math.max(max, dpMax[i]);
        }
        return max;
    }
}
