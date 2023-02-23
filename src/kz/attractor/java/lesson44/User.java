package kz.attractor.java.lesson44;

public class User {
    private String email;
    private String user;
    private String password;

    public User(String email, String user, String password) {
        this.email = email;
        this.user = user;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", email, user, password);
    }
}