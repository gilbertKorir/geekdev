package model;

import java.util.Objects;

public class jobs {
    private String title;
    private String description;
    private String duration;
    private String languages;
    private String link;
    private int id;

    public jobs(String title, String description, String duration, String languages, String link) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.languages = languages;
        this.link = link;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        jobs jobs = (jobs) o;
        return id == jobs.id &&
                Objects.equals(title, jobs.title) &&
                Objects.equals(description, jobs.description) &&
                Objects.equals(duration, jobs.duration) &&
                Objects.equals(languages, jobs.languages) &&
                Objects.equals(link, jobs.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, duration, languages, link, id);
    }
}
