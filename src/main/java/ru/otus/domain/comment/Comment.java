package ru.otus.domain.comment;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comment")
public class Comment {

    @Id
    private Long id;
    private Long bookId;
    private String text;


    public Comment(Long bookId, String text) {
        this.bookId = bookId;
        this.text = text;
    }

    public Comment() {
    }
}