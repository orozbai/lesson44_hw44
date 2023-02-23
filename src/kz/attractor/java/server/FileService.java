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
import java.util.List;

public class FileService {
    private static final Gson GSON = new GsonBuilder().create();
    private static final Path PATH = Paths.get("data/books.json");
    private static final Path pathEMPLOYERS = Paths.get("data/employers.json");
    private static final Path pathRegisteredUsers = Paths.get("data/registeredUsers.json");

    public static List<Book> readFile() {
        String json = "";
        try {
            json = Files.readString(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GSON.fromJson(json, CollectionBook.class).getBooksList();
    }

    public static List<Employer> readFileEmployers() {
        String json = "";
        try {
            json = Files.readString(pathEMPLOYERS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GSON.fromJson(json, CollectionEmployers.class).getEmployersList();
    }

    public static List<User> readFileUser() {
        String json = "";
        try {
            json = Files.readString(pathRegisteredUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GSON.fromJson(json, CollectionUser.class).getUserList();
    }

    public static void writeFile(List<User> users) {
        List<User> existingUsers = readExistingUsers(); // считываем существующих пользователей из файла
        existingUsers.addAll(users); // добавляем новых пользователей в список
        String json = GSON.toJson(existingUsers); // сериализуем список в JSON
        try {
            Files.write(pathRegisteredUsers, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<User> readExistingUsers() {
        try {
            String json = new String(Files.readAllBytes(pathRegisteredUsers));
            Type type = new TypeToken<List<User>>(){}.getType();
            return GSON.fromJson(json, type);
        } catch (IOException e) {
            return new ArrayList<>(); // если файл не существует, возвращаем пустой список
        }
    }

}