package algorithm_4th.chapter2;

/**
 * 选择排序
 */
public class Selection extends SortTemplate {

    @Override
    void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i ,min);
        }
    }

    public static void main(String[] args) {
        SortTemplate selection = new Selection();
        main(selection);
    }
}
