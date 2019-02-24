package leetcode.array.easy.maxSubArry_53;

public class Solution {

    public static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length ==0) {
            return 0;
        }
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = i; j < nums.length; j++) {
                temp += nums[j];
                max = max > temp ? max : temp;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, -6, -1, 5, 4, -7, -2, 3};
        int i = Solution.maxSubArray(nums);
        int j = Solution.maxSubArray1(nums);
        System.out.println(i + ", " + j);
    }

    /**
     * 2. DP解法
     * @param nums 数组
     * @return 最大子序和
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length ==0) {
            return 0;
        }
        int temp = nums[0];
        int res = temp;
        for(int i = 0; i < nums.length; i++) {
            temp = Math.max(temp + nums[i], nums[i]);
            res= Math.max(res, temp);
        }
        return res;
    }
}
