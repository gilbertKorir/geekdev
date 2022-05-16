package model;

import java.util.Objects;

public class Developers {

    private int id;
    private String name;
    private String email;
    private String password;
    private String resume;

    public Developers(String name, String email, String password, String resume) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.resume = resume;
    }
}
