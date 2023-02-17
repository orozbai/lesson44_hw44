package kz.attractor.java.lesson44;

public class BookDataModel {
    private Book book;

    public BookDataModel() {
        this.book = new Book("Букварь", "Жукова", "Учебное пособие", 1538, "https://www.google.com/url?sa=i&url=http%3A%2F%2Fddt1nvkz.ru%2Fbukvar&psig=AOvVaw0kdlhBDQJ61ZmoLQs6djqV&ust=1676727460502000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCOi4qNfWnP0CFQAAAAAdAAAAABAE");
    }

    public Book getBook() {
        return book;
    }
}
