package offer.zuiXiaoDeKGeShuLcof_40;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getLeastNumbers1(new int[]{1, 7, 8, 2, 3, 1, 5, 0, 2}, 4));
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) return new int[0];
        int[] nums = new int[k];
        int size = 0;
        for (int num : arr) {
            int tmp = size;
            while (tmp > 0 && nums[tmp] >= num) {
                nums[tmp] = nums[tmp - 1];
                tmp--;
            }
            if (tmp != size || size <= k - 1)
                nums[tmp] = num;
            if (size < k - 1)
                size++;
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }

    /**
        桶排序
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) return new int[0];
        int[] help = new int[10001];
        for (int num : arr) {
            help[num] = help[num] + 1;
        }
        int[] nums = new int[k];
        for (int i = 0; i < help.length; i++) {
            while (help[i] != 0) {
                nums[--k] = i;
                help[i] = help[i] - 1;
                if (k == 0) break;
            }
            if (k == 0) break;
        }
        return nums;
    }

    /**
     * 堆排序
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) return new int[0];
        Queue<Integer> queue = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if (queue.size() < k) {
                queue.offer(num);
            } else {
                if (queue.peek() > num) {
                    queue.poll();
                    queue.offer(num);
                }
            }
        }
        int[] nums = new int[k];
        for (int num : queue) {
            nums[--k] = num;
        }
        return nums;
    }
}
