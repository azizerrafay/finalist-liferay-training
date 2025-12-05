package nl.finalist.course.rest.model;

public class User {

    public User(String name, int id, String fullName, String email) {
        this.name = name;
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name;

    private int id;

    private String fullName;

    private String email;
}
