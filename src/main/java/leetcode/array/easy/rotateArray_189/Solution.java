package leetcode.array.easy.rotateArray_189;

public class Solution {
    /**
     * 法一：每次移动一个位置，一共移动p次。
     * 时间复杂度是O(pn),空间复杂度是O(1)。
     * @param nums
     * @param p
     */
    public void rotate(int[] nums, int p) {
        for (int i = 0; i < p; i++) {
            int temp = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }

    /**
     * 法二：对折翻转。首先翻转前len-p个元素，然后翻转之后的所有元素，最后把数组中的所有的元素都翻转。
     * 时间复杂度O(n)，空间复杂度O(1)。
     * @param nums
     * @param p
     */
    public void rotate1(int[] nums, int p) {
        int len = nums.length;
        p = p % len;
        int limit = len - p;
        reverse(nums, 0, limit - 1);
        reverse(nums, limit, len - 1);
        reverse(nums, 0, len - 1);
    }
    private void reverse(int[] nums, int left, int right) {
        while (right > left) {
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            right--;
            left ++;
        }
    }
}
