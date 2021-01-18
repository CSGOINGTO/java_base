package leetcode.array.medium.searchInRotatedSortedArray_II_81;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search1(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}, 2));
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 向下取整
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) return true;
            // 1. 判断在[left, mid - 1]还是[mid, right]区间中
            // target在[left, mid - 1]区间 [left, mid]区间递增
            if (nums[left] < nums[mid]) {
                // 保证target在[left, mid - 1]区间内
                if (target < nums[mid]) {
                    right = mid - 1;
                    // 否则target在[mid, right]区间内
                } else {
                    left = mid;
                }
                // target在[mid + 1, right]区间内
            } else {
                if (target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            System.out.println("left: " + left + " right: " + right);
        }
        return nums[left] == target;
    }

    public boolean search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 向下取整，当只有两个
            int mid = (left + right) >>> 1;
            // [left, mid - 1]
            if (nums[mid] > nums[left]) {
                // [left, target, mid]
                if (nums[left] <= target && target <= nums[mid]) {
                    // 落在前有序数组里
                    right = mid;
                    // [mid + 1, target, right]
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) {
                // 让分支和上面分支一样
                // [mid + 1, target, right]
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                // 要排除掉左边界之前，先看一看左边界可以不可以排除
                if (nums[left] == target) {
                    return true;
                } else {
                    left = left + 1;
                }
            }
            System.out.println("left: " + left + " right: " + right);
        }
        // 后处理，夹逼以后，还要判断一下，是不是 target
        return nums[left] == target;
    }
}
