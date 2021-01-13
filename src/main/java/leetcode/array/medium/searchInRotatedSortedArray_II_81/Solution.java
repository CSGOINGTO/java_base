package leetcode.array.medium.searchInRotatedSortedArray_II_81;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}, 2));
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) return true;
            // [mid, right]为递增区间
            if (nums[mid] <= nums[right]) {
                // target比[left, mid]区间值大，说明在[mid + 1, right]中
                if (nums[right] < target) {
                    left = mid + 1;
                    continue;
                }
                if (nums[mid] >= target && nums[right] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
                // [left, mid]为递增区间
            } else {
                // target不在[mid, right]区间内大，说明在[left, mid - 1]中
                if (nums[mid] < target) {
                    right = mid - 1;
                    continue;
                }
                if (nums[left] <= target && nums[mid] >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return nums[left] == target;
    }
}
