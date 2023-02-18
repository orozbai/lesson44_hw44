package kz.attractor.java.lesson44;

public class BookDataModel {
    private Book book;
    static EmployersDataModel emp = new EmployersDataModel();

    public BookDataModel() {
        this.book = new Book("Букварь", "Жукова", "Учебное пособие", 1538,
                "https://www.google.com/url?sa=i&url=http%3A%2F%2Fddt1nvkz.ru%2Fbukvar&psig=A" +
                        "OvVaw0kdlhBDQJ61ZmoLQs6djqV&ust=1676727460502000&source=images&cd=vfe&ved" +
                        "=0CBAQjRxqFwoTCOi4qNfWnP0CFQAAAAAdAAAAABAE", "Issued",
                emp.getEmployers().get(1).getName() + " " + emp.getEmployers().get(1).getSurname());
    }

    public Book getBook() {
        return book;
    }
}
