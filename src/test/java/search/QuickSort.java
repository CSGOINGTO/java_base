package search;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        new QuickSort().quickSort(new int[]{1,2,3,4,5,6,0}, 0, 6);
    }

    // 5,2,7,3,8,4,9
    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int low = left;
        int high = right;
        int target = arr[low];
        while (low < high) {
            while (arr[high] >= target && low < high) {
                high--;
            }
            while (arr[low] <= target && low < high) {
                low++;
            }
            if (low < high) {
                swap(arr, low, high);
            }
        }
        arr[left] = arr[low];
        arr[low] = target;
        System.out.println(Arrays.toString(arr));
        quickSort(arr, left, low - 1);
        quickSort(arr, low + 1, right);
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
