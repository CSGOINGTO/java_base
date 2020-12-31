package leetcode.math.medium.numberOfBoomerangs_447;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfBoomerangs(new int[][]{new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}}));
    }

    public int numberOfBoomerangs(int[][] points) {
        if (points.length <= 2) {
            return 0;
        }
        int res = 0;
        Map<Double, Integer> ijMap = new HashMap<>();
        Map<Double, Integer> ikMap = new HashMap<>();
        for (int[] point_i : points) {
            for (int[] point_j : points) {
                double i_j_len = Math.pow((point_j[0] - point_i[0]), 2) + Math.pow((point_j[1] - point_i[1]), 2);
                ijMap.put(i_j_len, ijMap.getOrDefault(i_j_len, 0) + 1);
                for (int[] point_k : points) {
                    double i_k_len = Math.sqrt(Math.pow((point_k[0] - point_i[0]), 2) + Math.pow((point_k[1] - point_i[1]), 2));
                    ikMap.put(i_k_len, ikMap.getOrDefault(i_k_len, 0) + 1);
                }
            }
        }
        for (Map.Entry<Double, Integer> doubleIntegerEntry : ijMap.entrySet()) {
            Integer i_k_count = ikMap.get(doubleIntegerEntry.getKey());
            if (i_k_count != null) {
                res = res + i_k_count * (doubleIntegerEntry.getValue() - 1);
            }
        }
        return res;
    }
}
