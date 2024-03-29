package ru.otus.domain.genre;

import jakarta.persistence.*;

@Entity
@Table(name = "GENRE")
public class Genre {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "GENRETITLE")
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
