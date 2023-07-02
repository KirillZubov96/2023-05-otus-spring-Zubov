package ru.otus.dao;

import ru.otus.domain.author.Author;
import ru.otus.domain.book.Book;
import ru.otus.domain.genre.Genre;

import java.util.List;

public interface LibraryDao {
    void storeBook(Book book);
    List<Book> getAllBooks();

    Book getBook(Book book);

    Book getBookByTitle(String title);

    Book getBookByID(Long id);

    List<Genre> getAllGenres();

    void removeBookById(Long id);

    Author getAuthorByName(String name);

    Genre getGenreByName(String name);
}
