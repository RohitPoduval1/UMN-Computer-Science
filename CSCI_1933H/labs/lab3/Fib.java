import java.util.Scanner;

public class Fib {
    // inefficient for large values of n (large values could be as small as n=20)
    static long[] storedFibs = new long[91];

    public static int fibonacciRecursive(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else {
            /*
             * fib(0) = 0
             * fib(1) = 1
             * fib(2) = 1
             * fib(3) = 2
             * fib(4) = 3 = fib(3) + fib(2)
             */
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    }

    public static int fibonacciIterative(int n) {
        int prev1 = 0;
        int prev2 = 1;
        int ans = 0;

        if (n == 0)
            return prev1;
        else if (n == 1)
            return prev2;
        else {
            for (int i = 1; i < n; i++) {
                ans = prev1 + prev2;
                prev1 = prev2;
                prev2 = ans;
            }
            return ans;
        }

    }

    /*
     * MEMOIZATION:
     * -Increase efficiency by storing intermediate results
     * -Works well when there is repetitive computations so they do not need to be
     * repeated
     * 1. Recursive solution
     * 2. Store (memoize)
     */
    public static long fibonacciMemoized(int n) {
        /*
         * 1. When a Fibonacci number is requested, the memoized data should be checked
         * 2. If the input number was previously computed, simply return it
         * 3. If it is not present, recursively calculate it
         */

        if (storedFibs[n] != 0) { // 1
            return storedFibs[n]; // 2
        } else { // 3
            if (n == 0) {
                storedFibs[0] = 0;
                return 0;
            } else if (n == 1) {
                storedFibs[1] = 1;
                return 1;
            } else {
                /*
                 * fib(0) = 0
                 * fib(1) = 1
                 * fib(2) = 1
                 * fib(3) = 2
                 * fib(4) = 3 = fib(3) + fib(2)
                 */
                long ans = fibonacciMemoized(n - 1) + fibonacciMemoized(n - 2);
                storedFibs[n] = ans;
                return ans;
            }
        }
    }

    public static void main(String args[]) {
        // System.out.print("Enter an integer n to get the n’th Fibonaccinumber: ");
        // Scanner myScanner = new Scanner(System.in);
        // int n = myScanner.nextInt(); // gets an integer from command line
        // System.out.println("The " + n + "’th Fibonacci number using
        // fibonacciRecursive is " + fibonacciRecursive(n));
        // System.out.println("The " + n + "’th Fibonacci number using
        // fibonacciIterative is " + fibonacciIterative(n));
        System.out.println("Memoized Fib: " + fibonacciMemoized(20));
        System.out.println("Memoized Fib: " + fibonacciMemoized(30));
        System.out.println("Memoized Fib: " + fibonacciMemoized(40));
        System.out.println("Memoized Fib: " + fibonacciMemoized(50));
        // System.out.println("Recursibe Fib: " + fibonacciRecursive(20));
        // System.out.println("Recursibe Fib: " + fibonacciRecursive(30));
        // System.out.println("Recursibe Fib: " + fibonacciRecursive(40));
        // System.out.println("Recursibe Fib: " + fibonacciRecursive(50));
    }

}
