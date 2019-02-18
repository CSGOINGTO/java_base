package leetcode.twoSum2_167;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 与twoSum相同的解法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int wanted = target - nums[i];
            if (map.containsKey(wanted)) {
                return new int[]{map.get(wanted) + 1, i + 1};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 因为数组是有序的，索引可以使用双索引进行碰撞查找
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[] {left + 1, right + 1};
            } else if (nums[left] + nums[right] < target) {
                left ++;
            } else {
                right --;
            }
        }
        return null;
    }
}
