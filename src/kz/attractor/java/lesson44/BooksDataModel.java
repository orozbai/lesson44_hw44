package kz.attractor.java.lesson44;

import java.util.ArrayList;
import java.util.List;


public class BooksDataModel {
    private List<Book> books = new ArrayList<>();
    static EmployersDataModel emp = new EmployersDataModel();


    public BooksDataModel() {
        books.addAll(List.of(
                new Book("Harry Potter: Part 1", "Some author", "Fantasy", 2000,
                        "./data/images/book.jpg", "Issued", emp.getEmployers().get(0).getName() + " " +
                        emp.getEmployers().get(0).getSurname()),
                new Book("Песнь льда и пламени", "Another some author", "Fantasy", 1997,
                        "./data/images/book.jpg", "Not issued", "None"),
                new Book("Wiki Book", "Another some author", "Wiki", 1997,
                        "./data/images/book.jpg", "Not issued", "None")
        ));
    }

    public List<Book> getBooks() {
        return books;
    }
}