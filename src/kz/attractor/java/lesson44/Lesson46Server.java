package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kz.attractor.java.server.ContentType;
import kz.attractor.java.server.Cookie;
import kz.attractor.java.server.FileService;
import kz.attractor.java.utils.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class Lesson46Server extends Lesson45Server {
    public Lesson46Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/give-books", this::giveBooksGet);
        registerPost("/give-books", this::giveBooksPost);
        registerGet("/return-books", this::returnBooksGet);
        registerPost("/return-books", this::returnBooksPost);
        registerGet("/logout", this::logoutGet);
    }

    private void returnBooksPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        int bookId = Integer.parseInt(parsed.get("bookId"));
        System.out.println(bookId);
        FileService.writeBookInformation(getLoginEmp().get(0).getEmail());
        redirect303(exchange,"/return-books");
    }

    private void returnBooksGet(HttpExchange exchange) {
        renderTemplate(exchange, "return-books.ftlh", getReturnBooks(exchange));
    }

    private Object getReturnBooks(HttpExchange exchange) {
        try {
            String email = loginEmp.get(0).getEmail();
            return new ReturnBookDataModel(email);
        } catch (NullPointerException e) {
            redirect303(exchange, "/login");
        }
        return "unknown";
    }

    private void logoutGet(HttpExchange exchange) {
        FileService.writeStatusCookie(1);
        Path path = makeFilePath("logout.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private int bookCount;

    private int booksCount;

    private void giveBooksPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        int bookId = Integer.parseInt(parsed.get("bookId"));
        boolean activeBooksCount = false;
        if (booksCount < 2) {
            booksCount = booksCount + Integer.parseInt(parsed.get("booksCount"));
        }
        if (checkUserCookie(exchange)) {
            var user = getLoginEmp();
            List<InformationBook> informationBooks = new ArrayList<>();
            for (int i = 0; i < FileService.readFileBookInformation().size(); i++) {
                if (FileService.readFileBookInformation().get(i).getEmail().equals(user.get(0).getEmail())
                        && FileService.readFileBookInformation().get(i).getFirstBook() > 0
                        && FileService.readFileBookInformation().get(i).getSecondBook() == 0) {
                    activeBooksCount = true;
                    FileService.replaceFileInformationBook(bookId, user.get(0).getEmail());
                    FileService.writeStatusCookie(1);
                }
            }
            if (activeBooksCount) {
                booksCount = 0;
            }
            if (bookCount == 0) {
                informationBooks.add(new InformationBook(user.get(0).getEmail(), bookId, 0, booksCount));
                FileService.writeFileInformationBook(informationBooks);
                informationBooks.clear();
                bookCount = 1;
            }
            redirect303(exchange, "/give-books");
        }
    }

    private void giveBooksGet(HttpExchange exchange) {
        renderTemplate(exchange, "give-books.ftlh", getGiveBooksDataModel());
    }

    private Object getGiveBooksDataModel() {
        return new GiveBookDataModel();
    }

    private static boolean creatingCookie;
    private static List<Employer> loginEmp;

    public static List<Employer> getLoginEmp() {
        return loginEmp;
    }

    public static void setLoginEmp(List<Employer> loginEmp) {
        Lesson46Server.loginEmp = loginEmp;
    }

    public static void checkCookie(List<Employer> list, HttpExchange exchange) {
        FileService.writeStatusCookie(2);

        setLoginEmp(list);

        if (!creatingCookie) {
            createCookie(list, exchange);
        }
    }

    private static void createCookie(List<Employer> list, HttpExchange exchange) {
        creatingCookie = true;
        Cookie sessionCookie = Cookie.make("mail", list.get(0).getEmail());
        sessionCookie.setMaxAge(600);
        sessionCookie.setHttpOnly(true);
        exchange.getResponseHeaders().add("Set-Cookie", sessionCookie.toString());
    }

    public static boolean checkUserCookie(HttpExchange exchange) {
        String cookieNotParsed = getCookies(exchange);
        Map<String, String> cookieParsed = Cookie.parse(cookieNotParsed);
        var CookieMail = cookieParsed.get("mail");
        var loginEmployerEmail = getLoginEmp().get(0).getEmail();
        return loginEmployerEmail.equals(CookieMail);
    }
}