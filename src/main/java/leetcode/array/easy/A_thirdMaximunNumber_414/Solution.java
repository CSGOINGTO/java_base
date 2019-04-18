package leetcode.array.easy.A_thirdMaximunNumber_414;

public class Solution {
    public int thirdMax(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        for(int num : nums) {
            first = Math.max(first, num);
        }
        for (int num : nums) {
            if (num != first) {
                second = Math.max(second, num);
            }
        }
        boolean exitThird = false;
        for (int num : nums) {
            if (num < second) {
                exitThird = true;
                third = Math.max(third, num);
            }
        }
        if (exitThird) {
            return third;
        }
        return first;
    }
}
