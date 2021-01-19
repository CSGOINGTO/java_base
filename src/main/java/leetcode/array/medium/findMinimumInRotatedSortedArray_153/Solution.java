package leetcode.array.medium.findMinimumInRotatedSortedArray_153;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMin(new int[]{4, 1}));
    }

    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 向下取整
            int mid = (left + right) >>> 1;
            // [left, mid]为递增区间
            if (nums[mid] >= nums[left]) {
                ans = Math.min(ans, nums[left]);
                // [mid + 1, right]找最小值
                left = mid + 1;
                // left > right时就退出了循环
//                if (left > nums.length - 1) break;

            } else { // [mid, right]为递增区间
                ans = Math.min(ans, nums[mid]);
                // [left, mid - 1]区间中找
                right = mid - 1;
                // left > right时就退出了循环
//                if (right < 0) break;
            }
            System.out.println("left: " + left + " mid: " + mid + " right: " + right);
        }
        return Math.min(ans, nums[left]);
    }
}
