package ru.otus.domain.book;

import jakarta.persistence.*;
import ru.otus.domain.author.Author;
import ru.otus.domain.comment.Comment;
import ru.otus.domain.genre.Genre;

import java.util.List;

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    private String title;

    @JoinColumn(name = "AUTHOR_ID")
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Author author;

    @JoinColumn(name = "GENRE_ID")
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Genre genre;

    @JoinColumn(name = "BOOKID")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Comment> comment;

    public Book() {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}