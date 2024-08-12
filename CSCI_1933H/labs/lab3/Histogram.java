import java.util.Arrays;

public class Histogram {
    public int lowerBound;
    public int upperBound;
    public int[] historray;

    public Histogram(int lowerBound, int upperBound) {
        if (lowerBound <= upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        } else { // if the upper bound is less than the lower bound, swap the bounds so the lower
                 // bound is always the smaller one
            this.lowerBound = upperBound;
            this.upperBound = lowerBound;
        }
        this.historray = new int[this.upperBound - this.lowerBound + 1];
    }

    /*
     * IMPLEMENTATION IDEA:
     * Dict like structure
     * Number and varOccurences
     * Add method increments varOccurences by one for that specific number
     * 
     * ! PROBLEM:
     * -There is an offset (can use index as number and value in array as the number
     * of occurrences)
     * -If the lower number is not zero, then there must be an offset to make the
     * index match the lower number
     * 
     * ! SOLUTION:
     * -Use lower bound as offset
     * ->index + lowerBound = number shown in histogram
     */
    public boolean add(int i) {
        if (i <= this.upperBound && i >= lowerBound) { // if i falls within the allowed range,
            this.historray[i - this.lowerBound] += 1; // add an occurence
        }
        return (i <= this.upperBound && i >= lowerBound);
    }

    public String toString() {
        String graph = ""; // foundation of histogram with no stars
        for (int i = 0; i < this.historray.length; i++) {
            graph += (i + this.lowerBound + ":");
            for (int j = 0; j < this.historray[i]; j++) {
                graph += ("*");
            }
            graph += "\n";
        }
        return graph;
    }

    public static void main(String args[]) {
        Histogram histo = new Histogram(0, 5);
        histo.add(3);
        histo.add(2);
        histo.add(1);
        histo.add(2);
        histo.add(3);
        histo.add(0);
        histo.add(1);
        histo.add(5);
        histo.add(3);
        histo.add(4);
        histo.add(4);
        histo.add(4);
        System.out.println(histo);
    }

}
