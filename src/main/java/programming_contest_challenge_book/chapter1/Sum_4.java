package programming_contest_challenge_book.chapter1;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sum_4 {

    private static int sum;

    private static int[] nums;

    public static void main(String[] args) throws ParseException {
        getTarget();
        solution();
        System.out.println("------------------------------分割线-------------------------------");
        solution1();
    }

    private static void solution() {
        final int sum = Sum_4.sum;
        final int[] nums = Sum_4.nums;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    for (int l = 0; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == sum) {
                            System.out.println(nums[i] + " " + nums[j] + " " + nums[k] + " " + nums[l]);
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void solution1() {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (binarySearch(nums, sum - nums[i] - nums[j] - nums[k])) {
                        System.out.println(nums[i] + " " + nums[j] + " " + nums[k]);
                    }
                }
            }
        }

    }

    private static void getTarget() {
        Map<String, Object> resMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        int sum = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        Sum_4.sum = sum;
        Sum_4.nums = nums;
    }


    private static boolean binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int middle = (l + r) / 2;
            if (nums[middle] == target) {
                return true;
            } else if (nums[middle] > target) {
                r = middle;
            } else {
                l = middle + 1;
            }
        }
        return false;
    }
}
