package leetcode.array.medium.containerWithMostWater_11;

public class Solution {

    /**
     * 暴力解法
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height.length <= 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int minHeight = Math.min(height[i], height[j]);
                max = Math.max(max, minHeight * (j - i));
            }
        }
        return max;
    }

    /**
     * 双指针法
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        if(height.length <= 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while(left < right) {
            int minHeight = height[left] < height[right] ? height[left ++] : height[right --];
            max = Math.max(max, minHeight * (right - left + 1));
        }
        return max;
    }
}
