package leetcode.array.medium.searchA2dMatrix_74;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.searchMatrix1(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 30));
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int row = matrix.length;
        int column = matrix[0].length;
        int left = 0;
        int right = row * column - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            // i--哪一行  j--具体行中的哪一列
            int i = mid / column;
            int j = mid % column;
            if (matrix[i][j] > target) right = mid - 1;
            else if (matrix[i][j] < target) left = mid + 1;
            else return true;
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int row = matrix.length;
        int column = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][column - 1] == target) return true;
                if (matrix[i][column - 1] < target) break;
                int left = 0;
                int right = column - 1;
                while (left < right) {
                    int mid = (left + right) >>> 1;
                    if (matrix[i][mid] == target) return true;
                    if (matrix[i][mid] > target) right = mid - 1;
                    if (matrix[i][mid] < target) left = mid + 1;

                }
                return matrix[i][left] == target;
            }
        }
        return false;
    }
}
