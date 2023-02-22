package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kz.attractor.java.Utils.Utils;
import kz.attractor.java.server.ContentType;
import kz.attractor.java.server.ResponseCodes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Lesson45Server extends Lesson44Server {
    public Lesson45Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
    }

    private void loginPost(HttpExchange exchange) {
//        String cType = getContentType(exchange);
//        String raw = getBody(exchange);
//
//        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
//
//        String data = String.format("<p>Необработанные данные: <b>%s</b></p>" +
//                "<p>Content-type: <b>%s</b></p>" +
//                "<p>После обработки: <b>%s</b></p>", raw, cType, parsed);
//        try {
//            sendByteData(exchange,
//                    ResponseCodes.OK,
//                    ContentType.TEXT_HTML,
//                    data.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        redirect303(exchange, "/books");
    }

    private void loginGet(HttpExchange exchange) {
        Path path = makeFilePath("login.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }
}
