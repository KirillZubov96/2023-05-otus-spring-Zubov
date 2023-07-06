package ru.otus.domain.comment;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "COMMENT")
public class Comment {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOOKID", updatable = false, nullable = false)
    private Long bookId;
    @Column(name = "COMMENTTEXT")
    private String text;


    public Comment(Long bookId, String text) {
        this.bookId = bookId;
        this.text = text;
    }

    public Comment() {
    }
}