package leetcode.array.easy.addToArrayFormOfInteger_989;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addToArrayForm2(new int[]{1, 2, 3, 12, 12, 12, 1, 21, 2, 1, 21, 2, 1, 2, 1, 21, 2, 1, 2, 12, 1, 2, 1, 21, 2, 1, 2, 12, 12, 1, 2, 12, 1, 2, 12, 1, 2, 12, 1, 2, 1, 21, 2, 12, 12, 1234123342, 34, 2, 34, 23, 4234}, 112312313));
    }

    /**
     * 结果溢出
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ans = new ArrayList<>();
        long num = 0;
        long index = 1;
        for (int i = A.length - 1; i >= 0; i--) {
            num = num + (A[i] * index);
            index *= 10;
        }
        num += K;
        String numStr = String.valueOf(num);
        for (int i = 0; i < numStr.length(); i++) {
            ans.add(numStr.charAt(i) - '0');
        }
        return ans;
    }

    public List<Integer> addToArrayForm2(int[] A, int K) {
        List<Integer> ans = new ArrayList<>();
        for (int i = A.length - 1; i >= 0; i--) {
            int tmp = A[i] + (K % 10);
            K /= 10;
            if (tmp >= 10) {
                ans.add(tmp % 10);
                K++;
            } else {
                ans.add(tmp);
            }
        }
        while (K != 0) {
            ans.add(K % 10);
            K /= 10;
        }
        Collections.reverse(ans);
        return ans;
    }
}
