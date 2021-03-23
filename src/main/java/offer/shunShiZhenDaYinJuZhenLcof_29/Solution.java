package offer.shunShiZhenDaYinJuZhenLcof_29;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int i = 1;
        if (2 <= ++i) System.out.println(i);

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        })));
    }


    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int row = matrix.length;
        int column = matrix[0].length;
        int[] res = new int[row * column];
        int t = 0, b = row - 1, l = 0, r = column - 1, index = 0;
        while (true) {
            for (int i = l; i <= r; i++) res[index++] = matrix[t][i];
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[index++] = matrix[i][r];
            if (--r < l) break;
            for (int i = r; i >= l; i--) res[index++] = matrix[b][i];
            if (--b < t) break;
            for (int i = b; i >= t; i--) res[index++] = matrix[i][l];
            if (++l > r) break;
        }
        return res;
    }
}
