package leetcode.plusOne_66;

public class Solution {
    public int[] plusOne(int[] digits) {
        int tab = (digits[digits.length - 1] + 1) / 10;
        digits[digits.length - 1] = (digits[digits.length - 1] + 1) % 10;
        for (int i = digits.length - 2; i >= 0; i--) {
            int temp = digits[i];
            digits[i] = (temp + tab) % 10;
            tab = (temp + tab) / 10;
        }
        if (tab == 1) {
            int[] res = new int[digits.length + 1];
            System.arraycopy(digits, 0, res, 1, digits.length);
            res[0] = 1;
            return res;
        }
        return digits;
    }
}
