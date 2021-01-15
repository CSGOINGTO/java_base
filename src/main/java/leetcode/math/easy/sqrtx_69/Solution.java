package leetcode.math.easy.sqrtx_69;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mySqrt(2147483647));
    }

    public int mySqrt(int x) {
        int left = 1;
        int right = x;
        while (left < right) {
            int mid = (left + right) >>> 1;
            long tmp = (long) mid * mid;
            if (tmp == x) return mid;
            else if (tmp > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if ((long)left * left > x) return left - 1;
        else if ((long) left * left < x) return left;
        else return left;
    }
}
