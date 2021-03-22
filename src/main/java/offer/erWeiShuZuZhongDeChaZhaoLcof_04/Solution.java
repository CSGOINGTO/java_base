package offer.erWeiShuZuZhongDeChaZhaoLcof_04;

class Solution {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        Solution solution = new Solution();
        System.out.println(solution.findNumberIn2DArray(matrix, 5));
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int column = matrix.length;
        int row = matrix[0].length;
        for (int i = 0; i < column; i++) {
            if (target > matrix[i][row - 1]) continue;
            if (target < matrix[i][0]) continue;
            int left = 0;
            int right = row - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (matrix[i][mid] == target) {
                    return true;
                }
                if (matrix[i][mid] < target) {
                    left = mid + 1;
                }
                if (matrix[i][mid] > target) {
                    right = mid - 1;
                }
            }
            if (matrix[i][left] == target) return true;
        }
        return false;
    }

    public boolean findNumberIn2DArray1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int column = 0;
        int row = matrix[0].length - 1;
        while(column < matrix.length && row >= 0) {
            if (matrix[column][row] == target) return true;
            if (matrix[column][row] > target) row--;
            else if (matrix[column][row] < target) column++;
        }
        return false;
    }
}
