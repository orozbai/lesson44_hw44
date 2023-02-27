package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kz.attractor.java.server.Cookie;
import kz.attractor.java.server.FileService;
import kz.attractor.java.utils.Utils;

import java.io.IOException;
import java.util.*;
import java.util.List;

public class Lesson46Server extends Lesson45Server {
    public Lesson46Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/give-books", this::giveBooksGet);
        registerPost("/give-books", this::giveBooksPost);
    }

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
                }
            }
            informationBooks.add(new InformationBook(user.get(0).getEmail(), bookId, 0, booksCount));
            if (activeBooksCount) {
                booksCount = 0;
            }
            FileService.writeFileInformationBook(informationBooks);
        } else {
            System.out.println("сообщение о том что надо залогиниться");
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
        Map<String, Object> userMap = new HashMap<>();
        setLoginEmp(list);
        // позже поменять на то чтобы при запросе стринг гетКукис эксчейндж если куки нету то ставить фолсе в иф
        if (!creatingCookie) {
            createCookie(list, exchange);
        }

        String cookieStr = getCookies(exchange);
        Map<String, String> cookieParse = Cookie.parse(cookieStr);
        userMap.put(cookieParse.get("mail"), list);

        System.out.println("куки стр " + cookieStr);
        System.out.println("куки парс" + cookieParse);
        System.out.println("user map " + userMap);
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