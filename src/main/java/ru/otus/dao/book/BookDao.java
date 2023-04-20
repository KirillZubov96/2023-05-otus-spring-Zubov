package ru.otus.dao.book;

import ru.otus.domain.book.Book;

import java.util.List;

public interface BookDao {
    long count();

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(int id);
}
