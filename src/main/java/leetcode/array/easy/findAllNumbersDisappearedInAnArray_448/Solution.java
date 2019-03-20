package leetcode.array.easy.findAllNumbersDisappearedInAnArray_448;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * 将以数组中出现的数字作为下标，将对应的元素置为负数，遍历数组，数组为正的下标
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (temp < 0) {
                temp = -temp;
            }
            if (nums[temp - 1] < 0) {
                nums[temp - 1] = -nums[temp - 1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    /**
     * 将数组中的元素放到对应下标的位置，遍历数组，数组下标和元素不对应的即是
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 当当前位置的数字和下标对应或者所要交换的数字一样时，就进入下次循环
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
