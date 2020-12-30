package leetcode.math.medium.numberOfBoomerangs_447;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        if (points.length <= 2) {
            return 0;
        }
        int res = 0;
        Map<Double, Double> helpMap = new HashMap<>();
        for (int[] point_i : points) {
            for (int[] point_j : points) {
                for (int[] point_k : points) {
                    if (point_i != point_j && point_i != point_k && point_j != point_k) {
                        double i_j_len = Math.sqrt(Math.pow((point_j[0] - point_i[0]), 2) + Math.pow((point_j[1] - point_i[1]), 2));
                        double i_k_len = Math.sqrt(Math.pow((point_k[0] - point_i[0]), 2) + Math.pow((point_k[1] - point_i[1]), 2));
                        if (i_j_len == i_k_len) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
