package leetcode.array.easy.plusOne_66;

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

    public int[] plusOne1(int[] digits) {
        boolean isUp = false;
        if ((digits[digits.length - 1] + 1) % 10 == 0) {
            isUp = true;
            digits[digits.length - 1] = 0;
        } else {
            digits[digits.length - 1] = digits[digits.length - 1] + 1;
        }
        for (int i = digits.length - 2; i >= 0; i--) {
            if (isUp) {
                digits[i] = (digits[i] + 1) % 10;
                if (digits[i] == 0) isUp = true;
                else isUp = false;
            }
        }
        if (digits[0] == 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                res[i + 1] = digits[i];
            }
            return res;
        }
        return digits;
    }
}
