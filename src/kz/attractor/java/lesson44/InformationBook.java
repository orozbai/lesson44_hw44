package kz.attractor.java.lesson44;

public class InformationBook {
    private String email;
    private int firstBook;
    private int secondBook;
    private int booksCount;

    public InformationBook(String email, int firstBook, int secondBook, int booksCount) {
        this.email = email;
        this.firstBook = firstBook;
        this.secondBook = secondBook;
        this.booksCount = booksCount;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(int booksCount) {
        this.booksCount = booksCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFirstBook() {
        return firstBook;
    }

    public void setFirstBook(int firstBook) {
        this.firstBook = firstBook;
    }

    public int getSecondBook() {
        return secondBook;
    }

    public void setSecondBook(int secondBook) {
        this.secondBook = secondBook;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", email, firstBook, secondBook, booksCount);
    }
}
