package algorithm_4th.chapter2;

/**
 * 插入排序
 */
public class Insertion extends SortTemplate {

    @Override
    void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        SortTemplate insertion = new Insertion();
        main(insertion);
    }
}
