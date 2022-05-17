package model;

import java.util.Objects;

public class Collaboration {
    private int id;
    private String username;
    private String project_code;

    public Collaboration(String username, String project_code) {
        this.username = username;
        this.project_code = project_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Collaboration)) return false;
        Collaboration that = (Collaboration) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(project_code, that.project_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, project_code);
    }
}
