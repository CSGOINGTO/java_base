package leetcode.array.medium.findAllDuplicatesInAnArray_442;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if(temp < 0) {
                temp = -temp;
            }
            if(nums[temp - 1] > 0) {
                nums[temp - 1] = -nums[temp - 1];
            }
        }
        for(int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if(temp < 0) {
                temp = -temp;
            }
            nums[temp - 1] = -nums[temp - 1];
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
