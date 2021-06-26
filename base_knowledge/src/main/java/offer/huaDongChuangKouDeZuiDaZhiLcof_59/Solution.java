package offer.huaDongChuangKouDeZuiDaZhiLcof_59;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-7, -8, 7, 5, 7, 1, 6, 0};
        System.out.println(Arrays.toString(solution.maxSlidingWindow1(nums, 4)));
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        if (k == 1) return nums;
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0, j = 1; i < nums.length; i++, j++) {
            if (j > k && deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            if (i >= k - 1) {
                res[i - k + 1] = deque.peekFirst();
            }
        }
        return res;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) return nums;
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        for (int i = k; i <= nums.length; i++) {
            int[] tmpArray = copyArray(nums, i - k, i);
            quickSort(tmpArray, 0, k - 1);
            res[i - k] = tmpArray[k - 1];
        }
        return res;
    }

    private int[] copyArray(int[] nums, int start, int end) {
        int[] res = new int[end - start];
        for (int i = end - start - 1; i >= 0; i--) {
            res[i] = nums[start++];
        }
        return res;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int l = left;
        int r = right;
        int target = nums[l];
        while (l < r) {
            while (nums[r] >= target && l < r) {
                r--;
            }
            while (nums[l] <= target && l < r) {
                l++;
            }
            if (l < r) {
                swap(nums, l, r);
            }
        }
        nums[left] = nums[l];
        nums[l] = target;
        quickSort(nums, left, l - 1);
        quickSort(nums, l + 1, right);
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
