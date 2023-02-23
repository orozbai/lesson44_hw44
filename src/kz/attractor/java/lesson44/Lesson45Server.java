package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kz.attractor.java.Utils.Utils;
import kz.attractor.java.server.ContentType;
import kz.attractor.java.server.FileService;
import kz.attractor.java.server.ResponseCodes;

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
        registerGet("/error", this::failedHandler);
        registerGet("/profile", this::profileGet);
        registerGet("/incorrect-login", this::incorrectLogin);
    }

    private void failedHandler(HttpExchange exchange) {
        Path path = makeFilePath("error.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
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
        if (correctEmail.equalsIgnoreCase(email) && correctPassword.equals(password)) {
            for (int i = 0; i < FileService.readFileEmployers().size(); i++) {
                if (email.equals(FileService.readFileEmployers().get(i).getEmail())
                        && password.equals(FileService.readFileEmployers().get(i).getPassword())) {
                    List<Employer> list = new ArrayList<>();
                    list.add(FileService.readFileEmployers().get(i));
                    FileService.writeFileProfile(list);
                    redirect303(exchange, "/profile");
                }
            }
        } else {
            List<Employer> list = new ArrayList<>();
            list.add(new Employer("some name", "none", "none", "none",
                    "none", "some user", "some password"));
            FileService.writeFileProfile(list);
            redirect303(exchange, "/incorrect-login");
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
        if (!empEmail.equalsIgnoreCase(email)) {
            list.add(new Employer("none", "none", "none", "none", email, user, password));
            FileService.writeFile(list);
            String msg = "Registration successful";
            try {
                sendByteData(exchange,
                        ResponseCodes.OK,
                        ContentType.TEXT_HTML,
                        msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> redirect303(exchange, "/error"), 2, TimeUnit.SECONDS);
        }
    }
}