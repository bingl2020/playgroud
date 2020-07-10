package DP;

public class Fibonacci {

    public int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 0;
        }

        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int x = a + b;
            a = b;
            b = x;
        }
String text = "🆕";
        text.replaceAll("[^\\u0000-\\uFFFF]", "");
        return b;

    }
}
