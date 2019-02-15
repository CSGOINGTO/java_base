package leetcode.removeDuplicates_26;

public class Solution {
    public int removeDuplicates (int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int len = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[len]) {
                nums[++len] = nums[i];
            }
        }
        return ++len;
    }
}
