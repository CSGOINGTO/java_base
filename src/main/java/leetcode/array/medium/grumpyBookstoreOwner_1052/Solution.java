package leetcode.array.medium.grumpyBookstoreOwner_1052;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSatisfied1(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 4));
    }

    public int maxSatisfied1(int[] customers, int[] grumpy, int X) {
        int ans = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) ans += customers[i];
        }
        int left = 0, right = 0;
        while (right < X) {
            if (grumpy[right] == 1) ans += customers[right];
            right++;
        }
        right--;
        int tmp_ans = ans;
        while (right < customers.length - 1) {
            if (grumpy[left] == 1) tmp_ans -= customers[left];
            left++;
            right++;
            if (grumpy[right] == 1) tmp_ans += customers[right];
            if (tmp_ans > ans) ans = tmp_ans;
        }
        return ans;
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int res = 0;
        int mins = customers.length;
        int grumpys = 0;
        for (int i = 0; i < mins; i++) {
            if (grumpy[i] == 1) grumpys++;
        }
        if (grumpys == 0) {
            for (int customer : customers) res += customer;
            return res;
        }

        for (int g = 1; g <= grumpys; g++) {
            int tmp_res = 0;
            int g_tmp = 0;
            for (int i = 0; i < mins; i++) {
                if (grumpy[i] == 0) tmp_res += customers[i];
                else {
                    g_tmp++;
                    if (g == g_tmp) {
                        for (int j = i; j < i + X && j < mins; j++) {
                            tmp_res += customers[j];
                        }
                        for (int k = i + X; k < mins; k++) {
                            if (grumpy[k] == 0) tmp_res += customers[k];
                        }
                        break;
                    }
                }
            }
            res = Math.max(res, tmp_res);
        }
        return res;
    }
}
