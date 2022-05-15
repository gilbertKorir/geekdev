package model;

import java.util.Objects;

public class contacts {

    private String name;
    private String title;
    private String comment;
    private String email;
    private String phone_no;
    private int id;

    public contacts(String name, String title, String comment, String email, String phone_no) {
        this.name = name;
        this.title = title;
        this.comment = comment;
        this.email = email;
        this.phone_no = phone_no;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        contacts contacts = (contacts) o;
        return id == contacts.id &&
                Objects.equals(name, contacts.name) &&
                Objects.equals(title, contacts.title) &&
                Objects.equals(comment, contacts.comment) &&
                Objects.equals(email, contacts.email) &&
                Objects.equals(phone_no, contacts.phone_no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, comment, email, phone_no, id);
    }
}
