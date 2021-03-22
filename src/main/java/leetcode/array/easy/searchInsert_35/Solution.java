package leetcode.array.easy.searchInsert_35;

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

    /**
     * left <= right表示在[left, right]这个区间中可能不存在目标值，
     * left的取值范围是[0, nums.length] right的取值范围是[-1, nums.length - 1]，因此最终返回的结果是left
     */
    public int searchInsert2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    /**
     * left < right表示在[left, right]区间中肯定存在目标值
     * left, right的取值范围都是[left, right]。
     */
    public int searchInsert3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length;
        while (right > left) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
