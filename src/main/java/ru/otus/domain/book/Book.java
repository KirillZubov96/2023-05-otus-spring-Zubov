package ru.otus.domain.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.otus.domain.author.Author;
import ru.otus.domain.genre.Genre;

@Setter
@Getter
@AllArgsConstructor
public class Book {

    private long id;

    private String name;

    private Author author;

    private Genre genre;

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
