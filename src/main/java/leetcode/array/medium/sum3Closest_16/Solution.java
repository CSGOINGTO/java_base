package leetcode.array.medium.sum3Closest_16;

import java.util.Arrays;

public class Solution {

    public int threeSumClosest(int[] nums, int target) {
        if(nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int minValue = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0; i < nums.length - 2; i++) {
            int num = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                int leftValue = nums[left];
                int rightValue = nums[right];
                int diff = target - num - leftValue - rightValue;
                if(diff == 0) {
                    return target;
                }
                int positiveDiff = diff > 0 ? diff : -diff;
                if(minValue > positiveDiff) {
                    minValue = positiveDiff;
                    res = num + leftValue + rightValue;
                }
                if(diff > 0) {
                    left++;
                    while(left < right && nums[left] == leftValue) {
                        left++;
                    }
                }
                if(diff < 0) {
                    right--;
                    while(left < right && nums[right] == rightValue) {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
