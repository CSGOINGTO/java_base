package leetcode.array.medium.sum4_18;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-5, 5, 4, -3, 0, 0, 4, -2};
        final List<List<Integer>> lists = solution.fourSum(nums, 4);
        lists.forEach(System.out::println);
    }

    /**
     * 暴力遍历法：注意对重复结果的去重操作
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        for (int i1 = 0; i1 < nums.length; i1++) {
            int temp1 = nums[i1];
            for (int i2 = 0; i2 < nums.length && i2 != i1; i2++) {
                int temp2 = nums[i2];
                for (int i3 = 0; i3 < nums.length && i3 != i2 && i3 != i1; i3++) {
                    int temp3 = nums[i3];
                    for (int i4 = 0; i4 < nums.length && i4 != i3 && i4 != i2 && i4 != i1; i4++) {
                        int temp4 = nums[i4];
                        if ((temp2 + temp3 + temp4 + temp1) == target) {
                            List<Integer> tempList = new ArrayList<>();
                            tempList.add(temp1);
                            tempList.add(temp2);
                            tempList.add(temp3);
                            tempList.add(temp4);
                            tempList.sort(Comparator.comparingInt(o -> o));
                            res.add(tempList);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }
}
