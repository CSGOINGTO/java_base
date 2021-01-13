package leetcode.array.medium.searchInRotatedSortedArray_33;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{3, 1}, 1));
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) return mid;
            // [left, mid]为递增区间，判断区间内的最小值和target的关系，即判断target是否存在于[left, mid]区间中
            // 当mid和left相等时，并且target在[mid + 1, right]区间内，保证能进入到这个区间内
            if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && nums[mid] >= target) {
                    right = mid - 1;
                    // 如果不在[left, mid]区间内，则去[mid + 1, right]区间去找target
                } else {
                    left = mid + 1;
                }
            } else {
                // [mid, right]为递增区间，判断区间内的最小值和target的关系
                if (nums[mid] <= target && nums[right] >= target) {
                    left = mid + 1;
                    // 如果不在[mid, right]区间内，则去[left, mid - 1]区间去找target
                } else {
                    right = mid - 1;
                }
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }
}
