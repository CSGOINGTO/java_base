package algorithm_4th.chapter2;

import algorithm_4th.util.StdRandom;
import algorithm_4th.util.Stopwatch;

public class SortCompare {
    static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) new Insertion().sort(a);
        if (alg.equals("Selection")) new Selection().sort(a);
        return timer.elapsedTime();
    }

    static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        double selection = timeRandomInput("Selection", 1000, 1000);
        double insertion = timeRandomInput("Insertion", 1000, 1000);
        System.out.println(selection);
        System.out.println(insertion);
        System.out.println(selection / insertion);

    }
}
