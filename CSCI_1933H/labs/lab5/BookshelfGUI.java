import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class BookshelfGUI implements ActionListener {
    private Bookshelf shelf;
    private final Button remove;
    private final JFrame frame;
    private final Bookpanel bp;
    private final Label bookInfo;

    private Book selectedBook;

    public BookshelfGUI(Bookshelf shelf) {
        this.shelf = shelf;

        frame = new JFrame();
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        remove = new Button("Remove random book");
        remove.addActionListener(this);
        remove.setActionCommand("Remove");
        remove.setBounds(450, 50, 200, 50);

        bp = new Bookpanel(this);
        bp.setBounds(50, 100, 400, 50);

        bookInfo = new Label("Click remove random book to get a book!");
        bookInfo.setBounds(50, 10, 400, 30);

        frame.add(remove);
        frame.add(bp);
        frame.add(bookInfo);

        frame.setVisible(true);
        bp.repaint();
    }

    public static void main(String[] args) throws FileNotFoundException {
        // TODO:
        //  - create a bookshelf object to pass in to the GUI constructor
        //  - create a new BookshelfGUI object which will display the GUI just by creating the object
        JFrame frame = new JFrame();
        Bookshelf bs = BookshelfReader.readBooksFromFile("bookinput.txt");
        BookshelfReader.writeShelfToFile(bs, "output.txt");
        BookshelfGUI g = new BookshelfGUI(bs);
    }

    public void repaint(Graphics g) {

        if (selectedBook == null) {
            g.setColor(Color.BLACK);
        } else {
            // TODO: change the color of the graphics context depending on the first letter of the authors name
            //  Be sure include at least 4 colors to cover the spread of the alphabet

            switch(selectedBook.getAuthor().charAt(1)) {
                case 'R':
                    g.setColor(Color.RED);
                    break;
                case 'C':
                    g.setColor(Color.YELLOW);
                    break;
                case 'G':
                    g.setColor(Color.GREEN);
                    break;
                case 'S':
                    g.setColor(Color.MAGENTA);
                    break;
                case 'T':
                    g.setColor(Color.BLUE);
                    break;
                case 'B':
                    g.setColor(Color.CYAN);
                    break;
                case 'O':
                    g.setColor(Color.ORANGE);
                    break;
                case 'P':
                    g.setColor(Color.DARK_GRAY);
                    break;
                case 'N':
                    g.setColor(Color.GRAY);
                    break;
                default:
                    g.setColor(Color.PINK);
                    System.out.println(selectedBook.getAuthor().charAt(1));
            }
        }
        // draws a filled in rectangle using the color selected in the switch statement
        g.fillRect(bookInfo.getX(), bookInfo.getY() + 75, bookInfo.getWidth(), bookInfo.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Remove")) {
            Book removed = shelf.removeBook();
            System.out.println(removed);
            if (removed != null) {
                bookInfo.setText(removed.getTitle());
                selectedBook = removed;
                repaint(frame.getGraphics()); // repaints the book panel
            }
            else {
                bookInfo.setText("There are no more books to remove :()");
            }
        }
    }
}

class Bookpanel extends JPanel {
    BookshelfGUI shelfGUI;
    public Bookpanel(BookshelfGUI shelfGUI) {
        this.shelfGUI = shelfGUI;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        shelfGUI.repaint(g);
    }
}
