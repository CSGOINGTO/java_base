package programming_contest_challenge_book.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class POJ1852 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int sum = Integer.parseInt(in.nextLine());
        List<String> resList = new ArrayList<String>();
        for (int i = 0; i < sum; i++) {
            String str = in.nextLine();
            int len = Integer.parseInt(str.split(" ")[0]);
            int n = Integer.parseInt(str.split(" ")[1]);
            int[] nums = new int[n];
            String[] strs = in.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                nums[j] = Integer.parseInt(strs[j]);
            }
            resolve(nums, len, resList);
        }
        resList.forEach(System.out::println);
    }

    private static void resolve(int[] nums, int l, List<String> resList) {
        int minT = 0;
        for (int i = 0; i < nums.length; i++) {
            minT = Math.max(minT, Math.min(nums[i], l - nums[i]));
        }
        int maxT = 0;
        for (int i = 0; i < nums.length; i++) {
            maxT = Math.max(maxT, Math.max(nums[i], l - nums[i]));
        }
        resList.add(minT + " " + maxT);
    }
}
