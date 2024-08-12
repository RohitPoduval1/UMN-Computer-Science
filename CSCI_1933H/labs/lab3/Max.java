import java.util.Scanner;

public class Max {
    // public static int recursiveMaxDigit(int n) {

    // }

    public static int recursiveMaxDigit(int n) {
        int currMax = n % 10;
        int newN = n / 10;
        if ((String.valueOf(n)).length() == 1) {
            return n;
        }

        else if (currMax < recursiveMaxDigit(newN)) {
            currMax = recursiveMaxDigit(newN);
        }
        return currMax;
    }

    public static int iterativeMaxDigit(int n) {
        int lengthOfN = (String.valueOf(n)).length();
        int[] digits = new int[lengthOfN];

        int nTemp = n;
        for (int i = 0; i < lengthOfN; i++) {
            digits[i] = nTemp % 10;
            nTemp /= 10;
        }

        int max = digits[0];
        for (int digit : digits) {
            if (digit > max)
                max = digit;
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.print("Enter an integer n to get the Max Digit: ");
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt(); // gets an integer from command line

        System.out.println("(Recursive) Max of: " + n + " is " + recursiveMaxDigit(n));
        System.out.println("(Iterative) Max of: " + n + " is " + iterativeMaxDigit(n));

    }
}
