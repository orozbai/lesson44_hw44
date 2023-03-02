package kz.attractor.java.server;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import kz.attractor.java.lesson44.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private static final Path pathHistoryBook = Paths.get("data/history-books.json");
    private static final Path pathEMPLOYERS = Paths.get("data/employers.json");
    private static final Path pathInformationBook = Paths.get("data/book-information.json");

    public static List<Book> readFile() {
        String json = "";
        try {
            json = Files.readString(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GSON.fromJson(json, CollectionBook.class).getBooksList();
    }

    public static void writeBookInformation(String email) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonElement root = gson.fromJson(new FileReader("data/book-information.json"), JsonElement.class);
            if (root.isJsonArray()) {
                JsonArray array = root.getAsJsonArray();
                for (int i = 0; i < array.size(); i++) {
                    JsonObject obj = array.get(i).getAsJsonObject();
                    if (obj.get("email").getAsString().equals(email)) {
                        array.remove(i);
                        i--;
                    }
                }
            } else if (root.isJsonObject()) {
                JsonObject object = root.getAsJsonObject();
                if (object.get("email").getAsString().equals(email)) {
                    object = null;
                }
            }

            try (FileWriter writer = new FileWriter("data/book-information.json")) {
                gson.toJson(root, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStatusCookie(int num) {
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(new FileReader("data/books.json"), JsonObject.class);
            JsonArray peopleArray = jsonObject.getAsJsonArray("books");
            for (int i = 0; i < peopleArray.size(); i++) {
                JsonObject personObject = peopleArray.get(i).getAsJsonObject();
                personObject.addProperty("status", num);
            }
            try (FileWriter writer = new FileWriter("data/books.json")) {
                gson.toJson(jsonObject, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            Type type = new TypeToken<List<Employer>>() {
            }.getType();
            return GSON.fromJson(json, type);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static List<HistoryBooks> readFileHistoryBooks() {
        String json = "";
        List<HistoryBooks> historyBooks = new ArrayList<>();
        try {
            Path path = Paths.get("data/history-books.json");
            json = Files.readString(path);
            historyBooks.addAll(Arrays.asList(GSON.fromJson(json, HistoryBooks[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyBooks;
    }
    public static void writeFileHistoryBooks(List<HistoryBooks> users) {
        List<HistoryBooks> existingUsers = readExistingHistoryBook();
        existingUsers.addAll(users);
        String json = GSON.toJson(existingUsers);
        try {
            Files.write(pathInformationBook, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<HistoryBooks> readExistingHistoryBook() {
        try {
            String json = new String(Files.readAllBytes(pathHistoryBook));
            Type type = new TypeToken<List<HistoryBooks>>() {
            }.getType();
            return GSON.fromJson(json, type);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static List<Employer> readFileEmployers() {
        String json = "";
        List<Employer> employers = new ArrayList<>();
        try {
            Path path = Paths.get("data/employers.json");
            json = Files.readString(path);
            employers.addAll(Arrays.asList(GSON.fromJson(json, Employer[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employers;
    }

    public static void writeFileProfile(List<Employer> profile) {
        String json = GSON.toJson(profile);
        try {
            Path path = Paths.get("data/profile.json");
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employer> readFileProfile() {
        String json = "";
        List<Employer> profile = new ArrayList<>();
        try {
            Path path = Paths.get("data/profile.json");
            json = Files.readString(path);
            profile.addAll(Arrays.asList(GSON.fromJson(json, Employer[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public static List<InformationBook> readFileBookInformation() {
        String json = "";
        List<InformationBook> information = new ArrayList<>();
        try {
            json = Files.readString(pathInformationBook);
            information.addAll(Arrays.asList(GSON.fromJson(json, InformationBook[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return information;
    }

    public static void writeFileInformationBook(List<InformationBook> users) {
        List<InformationBook> existingUsers = readExistingInformationBook();
        existingUsers.addAll(users);
        String json = GSON.toJson(existingUsers);
        try {
            Files.write(pathInformationBook, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<InformationBook> readExistingInformationBook() {
        try {
            String json = new String(Files.readAllBytes(pathInformationBook));
            Type type = new TypeToken<List<InformationBook>>() {
            }.getType();
            return GSON.fromJson(json, type);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void replaceFileInformationBook(int secondBookId, String email) {
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(pathInformationBook));
            JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
            for (JsonElement element : jsonArray) {
                if (element.getAsJsonObject().get("email").getAsString().equals(email)) {
                    element.getAsJsonObject().addProperty("secondBook", secondBookId);
                }
            }
            try (FileWriter writer = new FileWriter("data/book-information.json")) {
                gson.toJson(jsonArray, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}