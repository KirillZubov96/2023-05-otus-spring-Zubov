package ru.otus.service;

import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.LibraryDao;
import ru.otus.domain.author.Author;
import ru.otus.domain.book.Book;
import ru.otus.domain.comment.Comment;
import ru.otus.domain.genre.Genre;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final LibraryDao libraryDao;

    @Autowired
    public BookServiceImpl(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }


    @Override
    public void storeNewBook(String bookName, String authorName, String genreTitle) {
        Book newBook = new Book();
        //Ищем автора и жанр, если таких нет - создаем новые
        try {
            Author author = libraryDao.getAuthorByName(authorName);
            newBook.setAuthor(author);
        } catch (NoResultException ex) {
            newBook.setAuthor(new Author(authorName));
        }
        try {
            Genre genre = libraryDao.getGenreByName(genreTitle);
            newBook.setGenre(genre);
        } catch (NoResultException ex) {
            newBook.setGenre(new Genre(genreTitle));
        }
        Book foundedBook = libraryDao.getBook(newBook);
        if (foundedBook != null) {
            System.out.println("This book already here!");
            printInfoAboutBook(foundedBook.getTitle());
            return;
        }
        libraryDao.storeBook(newBook);
        System.out.println("Book is stored");
    }

    @Override
    public void printAllBooks() {
        System.out.println("Here is all book we have:");
        for (Book book :
                libraryDao.getAllBooks()) {
            printInfoAboutBook(book.getTitle());
        }
    }

    @Override
    public void printAllByGenre(String genre) {

    }

    @Override
    public void printByName(String name) {
        Book book = libraryDao.getBookByTitle(name);
        if (book != null) {
            printInfoAboutBook(book.getTitle());
        } else {
            System.out.println("No book found by Title " + name);
        }
    }

    @Override
    public void printAllGenres() {
        List<Genre> allGenres = libraryDao.getAllGenres();

        allGenres.forEach(g -> {
            System.out.println("====Genre====");
            System.out.println(g.toString());
        });
    }

    @Override
    public void addCommentToBook(String comment, Long bookId) {
        Book foundedBook = libraryDao.getBookByID(bookId);
        if (foundedBook == null) {
            System.out.printf("Book with ID %s not found%n", bookId);
            return;
        }
        foundedBook.getComment().add(new Comment(bookId, comment));
        libraryDao.storeBook(foundedBook);
        printInfoAboutBook(libraryDao.getBookByID(bookId).getTitle());
    }

    @Override
    public void deleteBook(Long id) {
        libraryDao.removeBookById(id);
        System.out.println("Done");
    }

    @Override
    public void printInfoAboutBook(String title) {
        Book book = libraryDao.getBookByTitle(title);
        System.out.println("==============");
        System.out.println("ID: " + book.getId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor().getName());
        System.out.println("Genre: " + book.getGenre().getTitle());
        book.getComment().forEach(c -> System.out.println("\nComment :" + c.getText()));
    }
}
