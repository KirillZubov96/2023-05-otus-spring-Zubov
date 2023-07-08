package ru.otus.service;

import ru.otus.domain.book.Book;

public interface BookService {

    void storeNewBook(String bookName, String authorName, String genreTitle);
    void printAllBooks();
    void printAllByGenre(String genre);
    void printByName(String name);

    void printAllAuthors();
    void printAllGenres();

    void printAllComments();
    void addCommentToBook(String comment, Long bookId);

    void deleteBook(Long id);

    void printInfoAboutBook(String title);
}
