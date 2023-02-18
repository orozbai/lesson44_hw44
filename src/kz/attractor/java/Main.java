package kz.attractor.java;

import kz.attractor.java.lesson44.Book;
import kz.attractor.java.lesson44.BooksDataModel;
import kz.attractor.java.lesson44.Lesson44Server;
import kz.attractor.java.server.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            new Lesson44Server("localhost", 9889).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}