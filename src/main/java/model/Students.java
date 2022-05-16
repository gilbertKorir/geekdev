package model;

import java.util.Objects;

public class Students {

    private String name;
    private String email;
    private String password;
    private int id;

    public Students(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Students)) return false;
        Students students = (Students) o;
        return id == students.id && Objects.equals(name, students.name) && Objects.equals(email, students.email) && Objects.equals(password, students.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, id);
    }

}
