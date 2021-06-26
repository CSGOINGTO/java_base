package offer.baShuZuPaiChengZuiXiaoDeShuLcof_45;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minNumber(new int[]{5, 9, 34, 30, 3}));
    }

    public String minNumber(int[] nums) {
        String[] arr = new String[nums.length];
        //解决大数问题，将数字转换为字符串
        for (int i = 0; i < nums.length; ++i) {
            arr[i] = String.valueOf(nums[i]);
        }

        quickSort(arr, 0, arr.length - 1);
        StringBuffer str = new StringBuffer();

        for (String x : arr) {
            str.append(x);
        }
        return str.toString();
    }

    public void quickSort(String[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int low = left;
        int hight = right;
        String pivot = arr[low];
        while (low < hight) {
            //比较大小
            while ((pivot + arr[hight]).compareTo(arr[hight] + pivot) <= 0 && low < hight) {
                hight--;
            }
            while ((pivot + arr[low]).compareTo(arr[low] + pivot) >= 0 && low < hight) {
                low++;
            }
            if (low < hight) {
                swap(arr, low, hight);
            }
        }
        arr[left] = arr[low];
        arr[low] = pivot;
        quickSort(arr, left, low - 1);
        quickSort(arr, hight + 1, right);
    }

    public void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
