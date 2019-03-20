package leetcode.array.easy.findAllNumbersDisappearedInAnArray_448;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void findDisappearedNumbers1() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        Solution solution = new Solution();
        List<Integer> disappearedNumbers1 = solution.findDisappearedNumbers1(nums);
        for (int num : disappearedNumbers1) {
            System.out.println(num);
        }
    }
}