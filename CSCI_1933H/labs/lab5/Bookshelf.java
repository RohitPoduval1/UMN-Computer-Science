import java.util.Random;
import java.util.random.*;;


public class Bookshelf {
    private Book[] bookShelf;

    public Bookshelf() {
        this.bookShelf = new Book[20];
    }

    public Bookshelf(int size) {
        this.bookShelf = new Book[size];
    }

    public Book[] getBooks() { return this.bookShelf; }

    public boolean add(Book newBook) {
        for (int i = 0; i < bookShelf.length; i++) {
            if (bookShelf[i] == null) {
                bookShelf[i] = newBook;
                return true;
            }
        }
        return false;
    }

    public Book removeBook() {
        // there are no books to remove
        if (bookShelf.length == 0) {
            return null;
        }

        // otherwise remove a random book from the available ones
        else {
            Random rand = new Random();
            Book chosenBook = bookShelf[rand.nextInt(this.bookShelf.length)];
            Bookshelf newBookshelf = new Bookshelf(this.bookShelf.length-1);
            
            int j = 0;
            for (int i = 0; i < this.bookShelf.length; i++) {
                if (!(this.bookShelf[i].equals(chosenBook))) {
                    newBookshelf.bookShelf[j++] = this.bookShelf[i];
                }
            }
            this.bookShelf = newBookshelf.bookShelf;
            return chosenBook;
        }
        
        
    }

    public Bookshelf getBooksByAuthor(String author) {
        int count = 0; 
        for (Book book : bookShelf) {
            if (book.getAuthor().equals(author)) {
                count++;
            }
        }

        Bookshelf toReturn = new Bookshelf(count);
        for (Book book : bookShelf) {
            if (book.getAuthor().equals(author)) {
                toReturn.add(book);
            }
        }
        return toReturn;
    }

    public String toString() {
        String toReturn= "";
        for (Book book : this.bookShelf) {
            if (! (book == null)) {
                toReturn = toReturn + book.toString() + "\n";
            }
        }
        return toReturn;
    }

    /*
     * Insertion sort based on compareTo() method
     * (a.compareTo(b)) returns...
        * Negative if a < b
        * Positive if a > b
        * O if a equals b 
     */
    public void sort(char sortBy) {
        Book n;
        int j;  // necessary to declare outside the loop to keep track of the location
                // so the new book can be place in the correct position later one

        // Iterate through each book of the bookShelf starting from the 2nd book since the first one is already sorted
        // by the way the algorithm works
        for (int i = 1; i < bookShelf.length; i++) {
            n = bookShelf[i];
            for (j = i-1; j >= 0 && n.compareTo(bookShelf[j], sortBy) < 0; j--) {
                bookShelf[j+1] = bookShelf[j];  // shift the elements by 1 to the right (by adding one) to make space for the new position of 'n'
            }
            bookShelf[j+1] = n;  // Place n in its new position
        }
    }

    public static void main(String[] args) {
        // Milestone 1 (and 0)
        Bookshelf bs = new Bookshelf(5);
        bs.add(new Book("Eragon", "Christopher Paolini", 10.0));
        bs.add(new Book("Eldest", "Christopher Paolini", 10.0));
        bs.add(new Book("Brisingr", "Christopher Paolini", 10.0));
        bs.add(new Book("Inheritance", "Christopher Paolini", 10.0));
        bs.add(new Book("Dracula", "Bram Stoker", 7.5));
        // Bookshelf goodbooks = bs.getBooksByAuthor("Christopher Paolini");
        // System.out.println(goodbooks);

        // Milestone 2
        // Bookshelf bs = new Bookshelf(5);
        // bs.add(new Book("Eragon", "Christopher Paolini", 10.0));
        // bs.add(new Book("The Fellowship of the Ring", "J.R.R. Tolkein", 10.0));
        // bs.add(new Book("Twilight", "Stephenie Meyer", 0.0));
        // bs.add(new Book("The Diary of a Wimpy Kid", "Jeff Kinney", 0.0));
        // bs.add(new Book("Dracula", "Bram Stoker", 7.5));
        // bs.sort('r');
        // System.out.println(bs);

        // // Milestone 5
        System.out.println(bs.getBooks().length);
        System.out.println(bs.removeBook());
        System.out.println(bs.removeBook());
        System.out.println(bs.removeBook());
        System.out.println(bs.removeBook());
        System.out.println(bs.removeBook());
        System.out.println(bs.getBooks().length);
    }
}
