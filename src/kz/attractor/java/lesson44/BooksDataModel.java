package kz.attractor.java.lesson44;

import java.util.ArrayList;
import java.util.List;


public class BooksDataModel {
    private List<Book> books = new ArrayList<>();

    public BooksDataModel() {
        books.addAll(List.of(
                new Book("Harry Potter: Part 1", "Some author", "Fantasy", 2000, "./data/images/book.jpg"),
                new Book("Песнь льда и пламени", "Another some author", "Fantasy", 1997, "./data/images/book.jpg")
        ));
    }

    public List<Book> getBooks() {
        return books;
    }
}
