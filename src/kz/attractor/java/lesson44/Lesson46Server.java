package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kz.attractor.java.server.Cookie;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Lesson46Server extends Lesson45Server {
    public Lesson46Server(String host, int port) throws IOException {
        super(host, port);
    }

    public static void creatingUnique(List<Employer> list, HttpExchange exchange) {
        String userId = UUID.randomUUID().toString();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put(userId, list);

        Cookie sessionCookie = Cookie.make(list.get(0).getEmail(), userId);
        sessionCookie.setMaxAge(600);
        sessionCookie.setHttpOnly(true);
        exchange.getResponseHeaders().add("Set-Cookie", sessionCookie.toString());
    }
}