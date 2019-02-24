package leetcode.array.easy.generate_118;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> generate(int numRow) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRow == 0) {
            return res;
        }
        List<Integer> r1 = new ArrayList<>();
        r1.add(1);
        res.add(r1);
        if (numRow == 1) {
            return res;
        }
        List<Integer> r2 = new ArrayList<>();
        r2.add(1);
        r2.add(1);
        res.add(r2);
        if (numRow == 2) {
            return res;
        }
        for (int i = 2; i < numRow; i++) {
            List<Integer> temp = new ArrayList<>();
            List<Integer> tab = res.get(i - 1);
            temp.add(1);
            for (int j = 0; j < i - 1; j++) {
                temp.add(tab.get(j) + tab.get(j + 1));
            }
            temp.add(1);
            res.add(temp);
        }
        return res;
    }
}
