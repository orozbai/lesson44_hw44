package kz.attractor.java.lesson44;

import kz.attractor.java.server.FileService;

import java.util.ArrayList;
import java.util.List;

public class ReturnBookDataModel {
    private final List<Book> books = new ArrayList<>();

    public ReturnBookDataModel(String email) {
        int firstBook = 0;
        int secondBook = 0;
        for (int i = 0; i < FileService.readFileBookInformation().size(); i++) {
            if (FileService.readFileBookInformation().get(i).getEmail().equals(email)) {
                firstBook = FileService.readFileBookInformation().get(i).getFirstBook() - 1;
                secondBook = FileService.readFileBookInformation().get(i).getSecondBook() - 1;
            } else {
                firstBook = -1;
                secondBook = -1;
            }
        }
        if (secondBook == 0 && firstBook == 0) {
            books.add(FileService.readFile().get(firstBook));
            books.add(FileService.readFile().get(secondBook));
        } else if (firstBook == -1 && secondBook != -1) {
            books.add(FileService.readFile().get(secondBook));
        } else if (secondBook == -1 && firstBook != -1) {
            books.add(FileService.readFile().get(firstBook));
        }
    }


    public List<Book> getBooks() {
        return books;
    }
}
