import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class OwlPopulation {
    private String fileName;
    private Owl[] data;


    public int populateData() throws FileNotFoundException {
        File f = new File(fileName);
        Scanner scanner = new Scanner(f);

        int numLines = 0;
        while(scanner.hasNextLine()){
            numLines++;
            String s = scanner.nextLine();
        }
        scanner.close();

        data = new Owl[numLines];   // data is allocated the exact amount of space it needs
        scanner = new Scanner(f);

        for (int i=0; i < data.length; i++) {
            String nextLine = scanner.nextLine();
            String[] info = nextLine.split(",");
            
            Owl owl = new Owl(info[0], Integer.parseInt(info[1]), Double.parseDouble(info[2]));
            data[i] = owl;
        }
        return data.length; 
    }

    public OwlPopulation(String fileName) throws FileNotFoundException {
        this.fileName = fileName; 
        populateData();
    }

    public double averageAge(){
        double ans = 0;
        for (Owl owl : data) {
            ans += owl.getAge();
        }
        ans /= data.length;

        return ans;
    }

    public Owl getYoungest(){
        double minAge = data[0].getAge();
        for (Owl owl : data) {
            if (owl.getAge() < minAge) {
                minAge = owl.getAge();
            }
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i].getAge() == minAge) {
                return data[i];
            }
        }
        return null;
    }

    public Owl getHeaviest(){
        double maxWeight = data[0].getWeight();
        for (Owl owl : data) {
            if (owl.getWeight() > maxWeight) {
                maxWeight = owl.getWeight();
            }
        }

        for (Owl owl : data) {
            if (owl.getWeight() == maxWeight) {
                return owl;
            }
        }
        return null;
    }

    public String toString() {
        String youngest = "The youngest owl is " + getYoungest().getName() + ", which is " + getYoungest().getAge() + " years old." + "\n";
        String heaviest = "The heavies owl is " + getHeaviest().getName() + ", which weighs " + getHeaviest().getWeight() + " pounds." + "\n";
        String average = "The average age of the population is " + averageAge();
        return youngest + heaviest + average;
    }

    public boolean containsOwl(Owl other){
        for (Owl owl : this.data) {
            if (owl.equals(other)) {
                return true;
            }
        }
        return false;
    }

    public void merge(OwlPopulation other) {
        
        int numOfDistinctOwls = 0;

        // Get number of distinct Owls from the other population
        // for purpose of creating the array of distinct owls
        for (int i = 0; i < other.data.length; i++) {
            if (! (this.containsOwl(other.data[i]))) {
                numOfDistinctOwls++;
            }
        }

        Owl[] distinctOwls = new Owl[this.data.length + numOfDistinctOwls];
        // Populate the distinctOwls array with the owls from this population
        for (int i = 0; i < this.data.length; i++) {
            distinctOwls[i] = this.data[i];
        }

        /*
        TODO: I think this is the part that doesn't work properly
        -I think the NullPointerException is coming from the fact that...
            distinctOwls = ~43 and other.data.length = ~67
        so about 20 elements are being skipped over initialization so they stick with their default value null, which causes the error
        (example: what if the unique owls are at the end??? they are completely skipped over because of the loop)
            -> changing the loop to be over other.data.length would make the array go out of bounds because
            if a unique owl is the very last element of other.data, java does not have the capability to append to arrays 
        */
        // Populate the distinctOwls array with the owls from the other population
        int currOwl = this.data.length; 
        for (int i = 0; i < other.data.length; i++) {  
            if (! (this.containsOwl(other.data[i]))) {
                distinctOwls[currOwl] = other.data[i];
                currOwl++;
            }
        }
        this.data = distinctOwls;
    }

    // Overloaded merge() for (Honors) Milestone 5 
    public void merge(String name) throws IOException {
        File f = new File(name);
        f.createNewFile();  // Create a new file titled with the given name
        FileWriter fw = new FileWriter(f, true);
        
        // Write the data from this.data to the file
        for (Owl owl : this.data) {
            fw.append(owl.getName() + "," + owl.getAge() + "," + owl.getWeight() + "\n");
            // Name, age, weight is how it will show up in the file
        }
        fw.flush();
        fw.close();
    }

    public int popSize(){
        return data.length;
    }
	
    public static void main(String[] args) throws IOException {
        try {
            //The following should run when you are complete. Feel free to comment out as you see fit while you work.
            OwlPopulation pop1 = new OwlPopulation("owlPopulation1.csv");
            System.out.println(pop1);
            System.out.println(pop1.popSize());

            OwlPopulation pop2 = new OwlPopulation("owlPopulation2.csv");
            System.out.println(pop2);
            System.out.println(pop2.popSize());

            pop1.merge(pop2);
            System.out.println(pop1);
            System.out.println(pop1.popSize());
            pop1.merge("output.csv");  // Milestone 5: overloaded merge to write to a csv file
        }
        catch (FileNotFoundException f){
            System.out.println("File not found.");
        }
    }
}