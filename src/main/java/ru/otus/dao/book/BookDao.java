package ru.otus.dao.book;

import ru.otus.domain.book.Book;

import java.util.List;

public interface BookDao {
    long count();

    void insert(String name, int authorId, int genreId);

    Book getById(int id);

    List<Book> getAll();

    void deleteById(int id);
}
