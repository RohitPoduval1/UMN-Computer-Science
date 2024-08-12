import java.util.Scanner;

public class HistogramApp {
    public void beginningPrompt() {
        System.out.println("---Histogram Console---");
        System.out.println("Options");
        System.out.println("add - used to add numbers to the histogram");
        System.out.println("print - prints the histogram to the screen");
        System.out.println("quit - leaves the program");
    }

    public int getLowerBound() {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter a lower bound: ");
        int lowerBound = myScanner.nextInt();
        return lowerBound;
    }

    public int getUpperBound() {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter an upper bound: ");
        int upperBound = myScanner.nextInt();
        return upperBound;
    }

    public static void main(String[] args) {
        HistogramApp myApp = new HistogramApp();

        myApp.beginningPrompt();
        int lowerBound = myApp.getLowerBound();
        int upperBound = myApp.getUpperBound();

        Histogram myHistogram = new Histogram(lowerBound, upperBound);

        Scanner myScanner = new Scanner(System.in);

        while (true) {
            System.out.print("Choose something: ");
            String nextAction = myScanner.nextLine().toLowerCase().trim();

            if (nextAction.equals("quit")) {
                System.out.println("Bye!");
                break;
            }

            else if (nextAction.equals("add")) {
                System.out.print("Enter a number: ");
                int toAdd = myScanner.nextInt();
                if (myHistogram.add(toAdd)) {
                    ;
                }

                else {
                    System.out.println(toAdd + " is not in the range");
                }
            }

            else if (nextAction.equals("print")) {
                System.out.println(myHistogram);

            }
        }

    }

}
