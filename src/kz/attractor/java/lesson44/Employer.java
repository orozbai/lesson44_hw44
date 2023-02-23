package kz.attractor.java.lesson44;

public class Employer {
    private String name;
    private String surname;
    private String readingBooks;
    private String currentBooks;
    private String email;
    private String user;
    private String password;

    public Employer(String name, String surname, String readingBooks, String currentBooks, String email, String user,
                    String password) {
        this.name = name;
        this.surname = surname;
        this.readingBooks = readingBooks;
        this.currentBooks = currentBooks;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return String.format("%s %s %s %s %s %s %s", name, surname, readingBooks, currentBooks, email, user, password);
    }
}