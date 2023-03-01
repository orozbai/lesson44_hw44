package kz.attractor.java.lesson44;

public class Book {
    private String name;
    private String author;
    private String genre;
    private Integer year;
    private String img;
    private String issued;
    private String employer;
    private int id;
    private int status;
    private String description;

    public Book(String name, String author, String genre, Integer year, String img, String issued, String employer,
                int id, int status, String description) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.img = img;
        this.issued = issued;
        this.employer = employer;
        this.id = id;
        this.status = status;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s", name, author, genre, year, issued, employer, id);
    }
}