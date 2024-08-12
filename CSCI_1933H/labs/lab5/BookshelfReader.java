import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BookshelfReader {
    
    /*
     * This method populates a bookshelf object with books from a text file
     * files are formatted in the form of
     * title, author, rating
     */
    public static Bookshelf readBooksFromFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner scanner = new Scanner(f);

        int numLines = 0;
        while(scanner.hasNextLine()){
            numLines++;
            String s = scanner.nextLine();
        }
        scanner.close();

        Bookshelf books = new Bookshelf(numLines);   // data is allocated the exact amount of space it needs
        scanner = new Scanner(f);

        for (int i=0; i < books.getBooks().length; i++) {
            String nextLine = scanner.nextLine();
            String[] info = nextLine.split(",");
            
            Book book = new Book(info[0], info[1], Double.parseDouble(info[2]));
            books.getBooks()[i] = book;
        }
        return books;
    }

    public static void writeShelfToFile(Bookshelf b, String fileName) throws FileNotFoundException {
        b.sort('r'); // sort by ratings

        Scanner s = null; // declare s outside try-catch block so it is not limited by scope
        try {
            s = new Scanner(new File(fileName));
        }
        catch (Exception e) { // notify user if fails to find fileName
            ;
        }

        // To write to an arbitrary textfile, do the following:
        // assume our filename is stored in the string fileName
        PrintWriter p = null; // declare p outside try-catch block
        try {
            p = new PrintWriter(new File(fileName));

            for (Book book : b.getBooks()) {
                p.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getRating());  // add book in the form of Title, Author, Rating followed 
                                                                                   // by newline character automatically (printLN!)
            }
            p.close();            //if you do not close the file, the output file will remain blank
        }

        catch (Exception e) {
            ;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Milestone 3
        Bookshelf bookShelf = BookshelfReader.readBooksFromFile("bookinput.txt");
        BookshelfReader.writeShelfToFile(bookShelf, "output.txt");

    }
}
