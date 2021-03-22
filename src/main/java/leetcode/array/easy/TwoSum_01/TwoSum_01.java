package leetcode.array.easy.TwoSum_01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 01
 *
 * @author Liuxue
 */
public class TwoSum_01 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numsMap.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = numsMap.get(target - nums[i]);
                return res;
            }
            numsMap.put(nums[i], i);
        }
        return res;
    }

    public static void main(String[] args) {
        TwoSum_01 solution = new TwoSum_01();
        System.out.println(Arrays.toString(solution.twoSum1(new int[]{2, 5, 5, 11}, 10)));
    }
}
