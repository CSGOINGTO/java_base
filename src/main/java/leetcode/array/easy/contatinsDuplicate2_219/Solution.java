package leetcode.array.easy.contatinsDuplicate2_219;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 法一：通过遍历直接判断
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearByDuplicate(int[] nums, int k) {
        if (nums.length == 0) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length && j <= i + k; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 法二：使用map来判断
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearByDuplicate1(int[] nums, int k) {
        if (nums.length == 1) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer temp = map.get(nums[i]);
                if (i - temp <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
