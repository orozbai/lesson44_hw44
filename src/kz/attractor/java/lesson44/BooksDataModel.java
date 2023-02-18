package kz.attractor.java.lesson44;

import kz.attractor.java.server.FileService;

import java.util.ArrayList;
import java.util.List;


public class BooksDataModel {
    private List<Book> books = new ArrayList<>();

    public BooksDataModel() {
        books.addAll(FileService.readFile());
    }

    public List<Book> getBooks() {
        return books;
    }
}