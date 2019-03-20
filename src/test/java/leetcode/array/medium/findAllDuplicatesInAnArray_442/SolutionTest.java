package leetcode.array.medium.findAllDuplicatesInAnArray_442;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void findDuplicates() {
        int[] nums = {4,3,2,7,8,2,3,1};
        Solution solution = new Solution();
        List<Integer> duplicates = solution.findDuplicates(nums);
        for (int num : duplicates) {
            System.out.println(num);
        }
    }
}