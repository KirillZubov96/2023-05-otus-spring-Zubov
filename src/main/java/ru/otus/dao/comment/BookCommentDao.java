package ru.otus.dao.comment;

import ru.otus.domain.book.Book;
import ru.otus.domain.comment.BookComment;

import java.util.List;

public interface BookCommentDao {
    long count();

    void insert(int book_id, String comment);

    BookComment getById(int id);

    List<BookComment> getAll();

    void deleteById(int id);

    BookComment getByBookId(long book_id);
}
