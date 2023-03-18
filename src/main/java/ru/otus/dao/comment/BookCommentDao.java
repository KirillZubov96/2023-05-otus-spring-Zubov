package ru.otus.dao.comment;

import ru.otus.domain.book.Book;
import ru.otus.domain.comment.BookComment;

import java.util.List;

public interface BookCommentDao {
    long count();

    void insert(long book_id, String comment);

    BookComment getById(long id);

    List<BookComment> getAll();

    void deleteById(long id);
}
