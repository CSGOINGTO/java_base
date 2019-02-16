package leetcode.merge_88;

public class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 记录插入的个数
        int count = 0;
        for(int i = 0; i < m + count; i++) {
            for(int j = count; j < n; j++) {
                if (nums1[i] > nums2[j]) {
                    System.arraycopy(nums1, i, nums1, i + 1, m + count - i);
                    nums1[i] = nums2[j];
                    count++;
                }
            }
        }
        // 插入的个数小于num2的有效长度
        if (count < n) {
            System.arraycopy(nums2, count, nums1, m + count, n - count);
        }
    }
}
