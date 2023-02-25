package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kz.attractor.java.utils.Utils;
import kz.attractor.java.server.ContentType;
import kz.attractor.java.server.FileService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Lesson45Server extends Lesson44Server {
    public Lesson45Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
        registerGet("/register", this::registerGet);
        registerPost("/register", this::registerPost);
        registerGet("/profile", this::unknownGet);
        registerGet("/login/profile", this::profileGet);
        registerGet("/incorrect-login", this::incorrectLogin);
    }

    private void unknownGet(HttpExchange exchange) {
        List<Employer> list = new ArrayList<>();
        list.add(new Employer("unknown", "unknown", "unknown", "unknown",
                "unknown", "unknown", "unknown"));
        FileService.writeFileProfile(list);
        renderTemplate(exchange, "profile.html", getUnknownProfileDataModel());
    }

    private Object getUnknownProfileDataModel() {
        return new UnknownDataModel();
    }

    private void incorrectLogin(HttpExchange exchange) {
        Path path = makeFilePath("incorrect-login.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void loginPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        String email = parsed.get("email");
        String password = parsed.get("password");
        var emp = FileService.readFileEmployers();
        String correctEmail = "";
        String correctPassword = "";
        for (Employer employer : emp) {
            if (employer.getEmail().equals(email) && employer.getPassword().equals(password)) {
                correctEmail = employer.getEmail();
                correctPassword = employer.getPassword();
            }
        }
        try {
            if (correctEmail.equalsIgnoreCase(email) && correctPassword.equals(password)) {
                for (int i = 0; i < FileService.readFileEmployers().size(); i++) {
                    if (email.equals(FileService.readFileEmployers().get(i).getEmail())
                            && password.equals(FileService.readFileEmployers().get(i).getPassword())) {
                        List<Employer> list = new ArrayList<>();
                        list.add(FileService.readFileEmployers().get(i));
                        FileService.writeFileProfile(list);
                        Lesson46Server.creatingUnique(list, exchange);
                        redirect303(exchange, "/login/profile");
                    }
                }
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            List<Employer> list = new ArrayList<>();
            list.add(new Employer("unknown", "unknown", "unknown", "unknown",
                    "unknown", "unknown", "unknown"));
            FileService.writeFileProfile(list);
            Path path = makeFilePath("incorrect-login.html");
            sendFile(exchange, path, ContentType.TEXT_HTML);
        }
    }

    private void profileGet(HttpExchange exchange) {
        renderTemplate(exchange, "profile.html", getProfileDataModel());
    }

    private Object getProfileDataModel() {
        return new ProfileDataModel();
    }

    private void loginGet(HttpExchange exchange) {
        Path path = makeFilePath("login.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void registerGet(HttpExchange exchange) {
        Path path = makeFilePath("register.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void registerPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        String email = parsed.get("email");
        String user = parsed.get("username");
        String password = parsed.get("password");
        List<Employer> list = new ArrayList<>();
        String empEmail = "";
        var empList = FileService.readFileEmployers();
        for (Employer employer : empList) {
            if (employer.getEmail().equalsIgnoreCase(email)) {
                empEmail = employer.getEmail();
            }
        }
        try {
            if (!empEmail.equalsIgnoreCase(email)) {
                list.add(new Employer("none", "none", "none", "none", email, user, password));
                FileService.writeFile(list);
                ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                executor.schedule(() -> redirect303(exchange, "/login"), 1, TimeUnit.SECONDS);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            Path path = makeFilePath("error.html");
            sendFile(exchange, path, ContentType.TEXT_HTML);
        }
    }
}