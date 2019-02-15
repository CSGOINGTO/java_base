package leetcode.searchInsert_35;

public class Solution {
    /**
     * 最最最容易想出来的方法，时间复杂度太高
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        // 如果target比数组中的所有数都大的话
        return nums.length;
    }

    /**
     * 使用二分查找的方法解决，时间复杂度为log n
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        // 位置在数组的最两边
        if (start == 0 || start == nums.length) {
            return start;
        }
        // 位置在数组中间
        return ((start + end) >>> 1) + 1;
    }
}
