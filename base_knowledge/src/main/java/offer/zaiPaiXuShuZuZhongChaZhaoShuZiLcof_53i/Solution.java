package offer.zaiPaiXuShuZuZhongChaZhaoShuZiLcof_53i;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search1(new int[]{5, 7, 7, 8, 8, 10}, 5));
    }

    public int search1(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] <= target) i = m + 1;
            else j = m - 1;
        }
        int right = i;
        if (j >= 0 && nums[j] != target) return 0;
        i = 0;
        j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] >= target) j = m - 1;
            else i = m + 1;
        }
        int left = j;
        return right - left - 1;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                res++;
                int tmp = mid;
                while (tmp >= 1 && nums[--tmp] == target) {
                    res++;
                }
                tmp = mid;
                while (tmp < nums.length - 1 && nums[++tmp] == target) {
                    res++;
                }
                break;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (res == 0 && nums[left] == target) res++;
        return res;
    }
}
