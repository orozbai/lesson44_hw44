package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kz.attractor.java.Utils.Utils;
import kz.attractor.java.server.ContentType;
import kz.attractor.java.server.FileService;
import kz.attractor.java.server.ResponseCodes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Lesson45Server extends Lesson44Server {
    public Lesson45Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
        registerGet("/register", this::registerGet);
        registerPost("/register", this::registerPost);
    }

    private void loginPost(HttpExchange exchange) {
//        String raw = getBody(exchange);
//        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
//        String email = parsed.get("email");
//        String password = parsed.get("password");
//        List<Employer> list = new ArrayList<>();
//        for (int i = 0; i < FileService.readFileUser().size(); i++) {
//            if (email.equals(FileService.readFileUser().get(i).getEmail())
//                    && password.equals(FileService.readFileUser().get(i).getPassword())) {
//
//        }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void loginGet(HttpExchange exchange) {
        Path path = makeFilePath("register.html");
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
        List<User> list = new ArrayList<>();
        String empEmail = "";
        for (int i = 0; i < FileService.readFileEmployers().size(); i++) {
            if (FileService.readFileEmployers().get(i).getEmail().equalsIgnoreCase(email)) {
                empEmail = FileService.readFileEmployers().get(i).getEmail();
            }
        }
        if (!empEmail.equalsIgnoreCase(email)) {
            list.add(new User(email, user, password));
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
            System.out.println("<script>showNotification('Registration failed');</script>");
            String data = "Registration failed, try again\nredirect...";
            try {
                sendByteData(exchange,
                        ResponseCodes.OK,
                        ContentType.TEXT_HTML,
                        data.getBytes());
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        redirect303(exchange, "/register");
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 2000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}