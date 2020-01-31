package algorithm_4th.chapter2;

import java.util.Scanner;

/**
 * 排序模板
 */
abstract class SortTemplate {
    abstract void sort(Comparable[] a);

    /**
     * 判断两个元素大小
     *
     * @param v 元素v
     * @param w 元素w
     * @return true（v < w）
     * false（v > w）
     */
    static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换两个元素
     *
     * @param a 排序数组
     * @param i 交换元素i
     * @param j 交换元素j
     */
    static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 判断排序后的数组是否按照升序排序
     *
     * @param a 排序后的数组
     * @return true：数组符合升序的排序
     * false：数组不符合升序
     */
    static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    /**
     * 获得待排序数组
     *
     * @return 待排序数组
     */
    static Comparable[] getArray() {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        Comparable[] array = new Comparable[len];
        for (int i = 0; i < len; i++) {
            array[i] = scanner.next();
        }
        return array;
    }

    /**
     * 测试排序算法
     *
     * @param sortTemplate 排序算法实例
     */
    static void main(SortTemplate sortTemplate) {
        Comparable[] a = getArray();
        sortTemplate.sort(a);
        assert isSorted(a);
        for (Comparable comparable : a) {
            System.out.print(comparable + " ");
        }
        System.out.println();
    }
}
