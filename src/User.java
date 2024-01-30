package src;

public class User {
    private String name;
    private Book[] borrowedBooks;

    public User(String name) {
        this.name = name;
    }  
    public String getName() {
        return this.name;
    }
    public Book[] getBorrowedBooks() {
        return this.borrowedBooks;
    }
}
