package leetcode.array.easy.peakIndexInAMountainArray_852;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.peakIndexInMountainArray1(new int[]{0, 2, 1}));
    }

    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] > arr[right]) right--;
            else if (arr[right] > arr[left]) left++;
            else {
                left++;
                right--;
            }
        }
        return left;
    }

    public int peakIndexInMountainArray1(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // 向上取整
            int mid = (left + right + 1) >>> 1;
            // [mid - 1 , mid]递增区间
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else if (arr[mid] < arr[mid - 1]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
