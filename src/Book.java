package src;
public class Book {
    private String title;
    private String author;
    private String publisher;
    private boolean isAvailable;

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
    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void toBorrow() {
        this.isAvailable = false;
    }
    public void toReturn() {
        this.isAvailable = true;
    }
}
