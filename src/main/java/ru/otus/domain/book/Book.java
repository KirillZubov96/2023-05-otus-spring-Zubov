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
    private Integer author_id;
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = Genre.class)
    private Integer genre_id;

    public Book(String name, int author_id, int genre_id) {
        this.name = name;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }
}
