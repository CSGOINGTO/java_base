package leetcode.array.hard.medianOfTwoSortedArrays_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> nums = new ArrayList<>();
        for (int aNums1 : nums1) nums.add(aNums1);
        for (int aNums2 : nums2) nums.add(aNums2);
        // 采用的是TimSort排序算法，平均时间复杂度为O（nlog（n））,空间复杂度为O（n）
        Collections.sort(nums);
        if (nums.size() % 2 == 1) {
            return (double) nums.get(nums.size() / 2);
        } else {
            return ((double) (nums.get(nums.size() / 2 - 1) + nums.get(nums.size() / 2))) / 2;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 6};
        int[] b = new int[]{2, 3};

    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] nums;
        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        // 空间复杂度O（m + n）
        nums = new int[nums1Length + nums2Length];
        int numsLength = 0;
        int nums1LengthTemp = 0;
        int nums2LengthTemp = 0;
        // 将两个有序数组进行排序，来降低时间复杂度，时间复杂度为O（m + n）
        while (numsLength <= nums1Length + nums2Length) {
            if (nums1LengthTemp == nums1Length) {
                while (nums2LengthTemp < nums2Length) {
                    nums[numsLength++] = nums2[nums2LengthTemp++];
                }
                break;
            }
            if (nums2LengthTemp == nums2Length) {
                while (nums1LengthTemp < nums1Length) {
                    nums[numsLength++] = nums1[nums1LengthTemp++];
                }
                break;
            }
            nums[numsLength++] = nums1[nums1LengthTemp] > nums2[nums2LengthTemp] ? nums2[nums2LengthTemp++] : nums1[nums1LengthTemp++];
        }

        if (numsLength % 2 == 0) {
            return (nums[numsLength / 2 - 1] + nums[numsLength / 2]) / 2.0;
        } else {
            return nums[numsLength / 2];
        }
    }

    /**
     * 直接寻找中位数，而不用先将所有的数放到一起
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int nums1_site = 0, nums2_site = 0;
        int left = -1, right = -1;
        int count = 0;
        while (count++ <= (m + n) / 2) {
            right = left;
            if (nums1_site < m && (nums2_site >= n || nums1[nums1_site] < nums2[nums2_site])) {
                left = nums1[nums1_site++];
            } else {
                left = nums2[nums2_site++];
            }
        }
        if ((m + n) % 2 == 0) {
            return (left + right) / 2.0;
        } else {
            return left;
        }
    }
}
