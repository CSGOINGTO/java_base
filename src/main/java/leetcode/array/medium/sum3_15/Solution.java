package leetcode.array.medium.sum3_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <= 1) {
            return res;
        }
        Arrays.sort(nums);
        int len = 0;
        while (len < nums.length - 2){
            int target = -nums[len];
            int left = len + 1;
            int right = nums.length - 1;
            while (left < right) {
                int leftValue = nums[left];
                int rightValue = nums[right];
                int temp = leftValue + rightValue;
                if (temp < target) {
                    left++;
                    while(left < right && nums[left] == leftValue) {
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
            while(len < nums.length && nums[len] == -target) {
                len++;
            }
        }
        return res;
    }
}
