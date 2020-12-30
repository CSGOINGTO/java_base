package leetcode.array.medium.sum3_15;

import java.util.*;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <= 1) {
            return res;
        }
        Arrays.sort(nums);
        int len = 0;
        while (len < nums.length - 2) {
            int target = -nums[len];
            int left = len + 1;
            int right = nums.length - 1;
            while (left < right) {
                int leftValue = nums[left];
                int rightValue = nums[right];
                int temp = leftValue + rightValue;
                if (temp < target) {
                    left++;
                    while (left < right && nums[left] == leftValue) {
                        left++;
                    }
                } else if (temp > target) {
                    right--;
                    while (left < right && nums[right] == rightValue) {
                        right--;
                    }
                } else {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[len]);
                    list.add(leftValue);
                    list.add(rightValue);
                    res.add(list);
                    left++;
                    right--;
                    while (left < right && nums[left] == leftValue) {
                        left++;
                    }
                    while (left < right && nums[right] == rightValue) {
                        right--;
                    }
                }
            }
            len++;
            while (len < nums.length && nums[len] == -target) {
                len++;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        Set<List<Integer>> resSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int target = -nums[i];
            Map<Integer, Integer> helpMap = new HashMap<>();
            for (int j = 0; j < nums.length && j != i; j++) {
                if (helpMap.containsKey(target - nums[j])) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[j]);
                    list.add(nums[i]);
                    list.add(target - nums[j]);
                    Collections.sort(list);
                    resSet.add(list);
                }
                helpMap.put(nums[j], nums[j]);
            }
        }
        return new ArrayList<>(resSet);
    }

}
