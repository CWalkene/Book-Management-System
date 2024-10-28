package src;
public class Book {
    private final String title;
    private final String author;
    private final String publisher;
    private boolean isAvailable;

    private int borrowedBy = -1;

    public Book(String title, String author, String publisher, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isAvailable = isAvailable;
    }
    public String getTitle() {
        return this.title;
    }
    public String getAuthor() {
        return this.author;
    }
    public String getPublisher() {
        return this.publisher;
    }
    public int getBorrowedBy() {
        return this.borrowedBy;
    }
    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void toBorrow(int userIndex) {
        this.isAvailable = false;
        this.borrowedBy = userIndex;
    }
    public void toReturn() {
        this.isAvailable = true;
        this.borrowedBy = -1;
    }
}
