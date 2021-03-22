package leetcode.array.easy.binarySearch_704;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{0}, 0));
    }

    /**
     * left < right保证遍历到的区间范围是[left, right]
     * 初始化left = 0, right = nums.length
     * 如果在[0, nums,length]的范围内没有找到target的话，说明在该数组中不存在target
     * 注意：当left == right时，会退出循环，当退出循环时，需要判断left位置处的值是否与target相等。
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
            System.out.println("left: " + left + " mid: " + mid + " right: " + right);
        }
        if (nums[left] == target) return left;
        return -1;
    }
}
