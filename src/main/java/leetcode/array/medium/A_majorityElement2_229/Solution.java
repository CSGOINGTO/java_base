package leetcode.array.medium.A_majorityElement2_229;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /**
     * 法一：使用map进行计数，寻找计数符合规则的元素
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        if (nums.length == 1){
            res.add(nums[0]);
            return res;
        }
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int key : map.keySet()) {
            if (map.get(key) > nums.length / 3) {
                res.add(key);
            }
        }
        return res;
    }
}
