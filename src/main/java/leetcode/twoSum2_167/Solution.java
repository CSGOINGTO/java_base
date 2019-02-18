package leetcode.twoSum2_167;

import java.util.HashMap;
import java.util.Map;

public class Solution {
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
}
