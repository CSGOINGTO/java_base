package leetcode.array.easy.shortestDistanceToACharacter_821;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int[] shortestToChar(String S, char C) {
        List<Integer> c_index = new ArrayList<>();
        int[] res = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                c_index.add(i);
            }
        }
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            int start = c_index.get(index);
            if (start != 0) {
                for (; i <= start; i++) {
                    res[i] = start - i;
                }
            }
            int end = start;
            if ((index + 1) < c_index.size()) {
                end = c_index.get(++index);
            }
            int mid = (int) Math.ceil(((double) (start + end)) / 2);
            for (; i < end; i++) {
                if (i < mid) {
                    res[i] = i - start;
                } else {
                    res[i] = end - i;
                }
            }
            if (start == end) {
                for (; i < res.length; i++) {
                    res[i] = i - end;
                }
            }
        }
        return res;
    }
}
