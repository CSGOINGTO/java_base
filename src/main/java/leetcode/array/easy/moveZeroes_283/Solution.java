package leetcode.array.easy.moveZeroes_283;

public class Solution {
    /**
     * 法一：
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[sum++] = nums[i];
            }
        }
        for (int i = sum; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 法二
     * @param nums
     */
    public void moveZeroes1(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[sum];
                nums[sum++] = temp;
            }
        }
    }
}
