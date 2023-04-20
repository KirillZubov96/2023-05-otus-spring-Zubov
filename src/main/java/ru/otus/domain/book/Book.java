package ru.otus.domain.book;

import jakarta.persistence.*;
import lombok.*;
import ru.otus.domain.author.Author;
import ru.otus.domain.genre.Genre;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = Author.class)
    private Author author;
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = Genre.class)
    private Genre genre;

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
