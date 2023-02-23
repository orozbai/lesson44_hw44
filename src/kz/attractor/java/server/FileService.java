package kz.attractor.java.server;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import kz.attractor.java.lesson44.*;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("data/books.json");
    private static final Path pathEMPLOYERS = Paths.get("data/employers.json");

    public static List<Book> readFile() {
        String json = "";
        try {
            json = Files.readString(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GSON.fromJson(json, CollectionBook.class).getBooksList();
    }

    public static void writeFile(List<Employer> users) {
        List<Employer> existingUsers = readExistingUsers();
        existingUsers.addAll(users);
        String json = GSON.toJson(existingUsers);
        try {
            Files.write(pathEMPLOYERS, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Employer> readExistingUsers() {
        try {
            String json = new String(Files.readAllBytes(pathEMPLOYERS));
            Type type = new TypeToken<List<Employer>>(){}.getType();
            return GSON.fromJson(json, type);
        } catch (IOException e) {
            return new ArrayList<>(); // если файл не существует, возвращаем пустой список
        }
    }

    public static List<Employer> readFileEmployers() {
        String json = "";
        List<Employer> employers = new ArrayList<>();
        try{
            Path path = Paths.get("data/employers.json");
            json = Files.readString(path);
            employers.addAll(Arrays.asList(GSON.fromJson(json, Employer[].class)));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return employers;
    }
}