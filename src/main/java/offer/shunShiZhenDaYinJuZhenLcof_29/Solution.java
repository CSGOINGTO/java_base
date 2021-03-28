package offer.shunShiZhenDaYinJuZhenLcof_29;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
//                {13, 14, 15, 16}
        })));
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int b = matrix.length - 1;
        int r = matrix[0].length - 1;
        int[] res = new int[(b + 1) * (r + 1)];
        int index = 0;
        int l = 0;
        int t = 0;
        while (true) {
            for (int i = l; i <= r; i++) res[index++] = matrix[t][i];
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[index++] = matrix[i][r];
            if (--r < l) break;
            for (int i = r; i >= l; i--) res[index++] = matrix[b][i];
            if (--b > t) break;
            for (int i = b; i >= t; i--) res[index++] = matrix[i][l];
            if (++l > r) break;
        }
        return res;
    }
}
