package leetcode.math.medium.numberOfBoomerangs_447;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}}));
    }

    public int numberOfBoomerangs(int[][] points) {
        if (points.length <= 2) {
            return 0;
        }
        int res = 0;
        Map<Double, Integer> ijMap = new HashMap<>();
        for (int[] point_i : points) {
            for (int[] point_j : points) {
                if (point_i != point_j) {
                    double i_j_len = Math.pow((point_j[0] - point_i[0]), 2) + Math.pow((point_j[1] - point_i[1]), 2);
                    ijMap.put(i_j_len, ijMap.getOrDefault(i_j_len, 0) + 1);
                }
            }
            for (Integer value : ijMap.values()) {
                res += value * (value - 1);
            }
            ijMap.clear();
        }
        return res;
    }
}
