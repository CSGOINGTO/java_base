package programming_contest_challenge_book.chapter2;

public class Recur {

    public static void main(String[] args) {
        System.out.println(new Recur().fib1(40));
    }

    private int fib(int n) {
        if (n == 0 || n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    private int[] memo = new int[41];

    private int fib1(int n) {
        if (n <= 1) return 1;
        if (memo[n] != 0) return memo[n];
        return memo[n] = fib1(n - 1) + fib1(n - 2);
    }
}
