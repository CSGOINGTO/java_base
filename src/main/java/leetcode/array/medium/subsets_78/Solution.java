package leetcode.array.medium.subsets_78;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subsets2(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            List<Integer> subAns = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    subAns.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(subAns));
            System.out.println(ans);
        }
        return ans;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < (1 << len); i++) {
            List<Integer> subAns = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                // 比如用011表示要获取nums中下标为1,2位置的元素，则011 & 001 = 001（下标1） 和 011 & 010 = 010（下标2）可以满足要求。
                if ((i & (1 << j)) != 0) {
                    subAns.add(nums[j]);
                }
            }
            ans.add(subAns);
            System.out.println(ans);
        }
        return ans;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Deque<Integer> stack = new ArrayDeque<>();
        dfs(nums, 0, stack, res);
        return res;
    }

    private void dfs(int[] nums, int index, Deque<Integer> stack, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(stack));
            return;
        }
        dfs(nums, index + 1, stack, res);
        stack.addLast(nums[index]);
        dfs(nums, index + 1, stack, res);
        stack.removeLast();
    }

}
