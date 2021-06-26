package programming_contest_challenge_book.chapter1;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int len = nums[i] + nums[j] + nums[k];
                    int maxLen = Math.max(nums[i], Math.max(nums[j], nums[k]));
                    int rest = len - maxLen;
                    if (maxLen < rest) {
                        res = Math.max(res, len);
                    }
                }
            }
        }
        System.out.println(res);
    }
}
