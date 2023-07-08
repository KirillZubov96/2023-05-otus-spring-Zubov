package ru.otus.domain.genre;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genres")
public class Genre {

    @Id
    private Long id;

    private String title;

    public Genre(String genreTitle) {
        this.title = genreTitle;
    }

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
