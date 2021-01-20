package leetcode.array.hard.slidingWindowMaximum_239;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.maxSlidingWindow1(new int[]{9, 2, 1, 3, 4, 5}, 3)));
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        int len = nums.length;
        int[] ans_arr = new int[len - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int left = 0, right = 0, arr_index = 0;
        while (right < k) {
            while (deque.peekLast() != null && deque.peekLast() < nums[right]) {
                deque.removeLast();
            }
            deque.addLast(nums[right]);
            right++;
        }
        ans_arr[arr_index++] = deque.peekFirst();
        while (right < len - 1) {
            if (deque.peekFirst() == nums[left]) {
                deque.removeFirst();
            }
            right++;
            left++;
            while (deque.peekFirst() != null && deque.peekLast() < nums[right]) {
                deque.removeLast();
            }
            deque.addLast(nums[right]);
            ans_arr[arr_index++] = deque.peekFirst();
        }
        return ans_arr;
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        int[] ans = new int[len - k + 1];
        int index = 0;
        int tmp_ans = Integer.MIN_VALUE;
        while (right < k) {
            tmp_ans = Math.max(tmp_ans, nums[right]);
            right++;
        }
        ans[index] = tmp_ans;
        index++;
        right--;
        while (right < len - 1) {
            tmp_ans = Integer.MIN_VALUE;
            left++;
            right++;
            for (int i = left; i <= right; i++) {
                tmp_ans = Math.max(nums[i], tmp_ans);
            }
            ans[index] = tmp_ans;
            index++;
        }
        return ans;
    }
}
