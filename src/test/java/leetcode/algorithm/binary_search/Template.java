package leetcode.algorithm.binary_search;

public class Template {

    public static void main(String[] args) {
        Template template = new Template();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3, 4, 4, 5, 6, 7, 7, 8, 10};
        System.out.println(template.searchL(nums, 10));
        System.out.println(template.searchR(nums, 10));
    }

    private int searchL(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 向上取整（因为要从后往前找到最小的一个符合条件的下标）
            int mid = (left + right + 1) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int searchR(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 向下取整
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
