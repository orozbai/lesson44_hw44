package kz.attractor.java.lesson44;

public class BookDataModel {
    private Book book;
    static EmployersDataModel emp = new EmployersDataModel();
    static BooksDataModel books = new BooksDataModel();

    public BookDataModel() {
        this.book = new Book(books.getBooks().get(3).getName(), books.getBooks().get(3).getAuthor(),
                books.getBooks().get(3).getGenre(), books.getBooks().get(3).getYear(),
                "https://img.freepik.com/free-vector/neon-lights-background-theme_52683-44625.jpg",
                books.getBooks().get(3).getIssued(),
                emp.getEmployers().get(1).getName() + " " + emp.getEmployers().get(1).getSurname());
    }

    public Book getBook() {
        return book;
    }
}
