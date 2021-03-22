package leetcode.array.medium.findFirstAndLastPositionOfElementInSortedArray_34;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.searchRange1(new int[]{2, 2}, 2)));
    }

    /**
     * left < right说明区间范围是在[left, right]
     * 结束循环时(left == right)之后要判断nums[left]是否与target相等
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid - 1;
                while (left >= 0 && nums[left] == target) {
                    left--;
                }
                right = mid + 1;
                while (right < nums.length && nums[right] == target) {
                    right++;
                }
                return new int[]{left + 1, right - 1};
            } else if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        if (nums[left] == target) return new int[]{left, left};
        return new int[]{-1, -1};
    }

    public int[] searchRange1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int left = searchLeft(nums, target);
        int right = searchRight(nums, target);
        if (left > right || (left == right && nums[left] != target)) return new int[]{-1, -1};
        return new int[]{left, right};
    }

    private int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
