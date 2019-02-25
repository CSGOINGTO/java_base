package leetcode.array.easy.majorityElement_169;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 哈希表法
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
            if (map.get(nums[i]) > nums.length / 2) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 摩尔投票法
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                ++count;
            } else if (num == candidate) {
                ++count;
            } else {
                --count;
            }
        }
        return candidate;
    }
}
