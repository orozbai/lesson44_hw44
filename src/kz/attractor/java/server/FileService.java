package kz.attractor.java.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.attractor.java.lesson44.Book;
import kz.attractor.java.lesson44.CollectionBook;
import kz.attractor.java.lesson44.CollectionEmployers;
import kz.attractor.java.lesson44.Employer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileService {
    private static final Gson GSON = new GsonBuilder().create();
    private static final Path PATH = Paths.get("data/books.json");
    private static final Path pathEMPLOYERS = Paths.get("data/employers.json");
    public static List<Book> readFile()  {
        String json = "";
        try {
            json = Files.readString(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GSON.fromJson(json, CollectionBook.class).getBooksList();
    }

    public static List<Employer> readFileEmployers()  {
        String json = "";
        try {
            json = Files.readString(pathEMPLOYERS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GSON.fromJson(json, CollectionEmployers.class).getEmployersList();
    }
}
