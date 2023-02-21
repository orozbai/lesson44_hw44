package kz.attractor.java.lesson44;

public class Employer {
    private String name;
    private String surname;
    private String readingBooks;
    private String currentBooks;

    public Employer(String name, String surname, String readingBooks, String currentBooks) {
        this.name = name;
        this.surname = surname;
        this.readingBooks = readingBooks;
        this.currentBooks = currentBooks;
    }

    public String getReadingBooks() {
        return readingBooks;
    }

    public void setReadingBooks(String readingBooks) {
        this.readingBooks = readingBooks;
    }

    public String getCurrentBooks() {
        return currentBooks;
    }

    public void setCurrentBooks(String currentBooks) {
        this.currentBooks = currentBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", name, surname, readingBooks, currentBooks);
    }
}