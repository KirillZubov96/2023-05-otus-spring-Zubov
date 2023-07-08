package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.*;
import ru.otus.domain.author.Author;
import ru.otus.domain.book.Book;
import ru.otus.domain.comment.Comment;
import ru.otus.domain.genre.Genre;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public void storeNewBook(String bookName, String authorName, String genreTitle) {

        Book foundedBook = bookRepository.findByTitleAndAuthorNameAndGenreTitle(bookName, authorName, genreTitle);
        if (foundedBook != null) {
            System.out.println("This book already here!");
            printInfoAboutBook(foundedBook.getTitle());
            return;
        }
        Book newBook = new Book();
        newBook.setTitle(bookName);
        newBook.setAuthor(new Author(authorName));
        newBook.setGenre(new Genre(genreTitle));
        //newBook -t tttt -a aaaaa -g ggggg
        bookRepository.save(newBook);
        System.out.println("Book is stored");
    }

    @Override
    public void printAllBooks() {
        System.out.println("Here is all book we have:");
        bookRepository.findAll().forEach(book -> printInfoAboutBook(book.getTitle()));
    }

    @Override
    public void printAllByGenre(String genre) {

    }

    @Override
    public void printByName(String name) {
        Book book = bookRepository.findByTitle(name);
        if (book != null) {
            printInfoAboutBook(book.getTitle());
        } else {
            System.out.println("No book found by Title " + name);
        }
    }

    @Override
    public void printAllAuthors() {
        authorRepository.findAll().forEach(author -> {
            System.out.println("====Author====");
            System.out.println(author.toString());
        });
    }

    @Override
    public void printAllGenres() {
        List<Genre> allGenres = genreRepository.findAll();
        allGenres.forEach(g -> {
            System.out.println("====Genre====");
            System.out.println(g);
        });
    }

    @Override
    public void printAllComments() {
        commentRepository.findAll().forEach(System.out::println);
    }

    @Override
    public void addCommentToBook(String comment, Long bookId) {
        Book foundedBook = bookRepository.findById(bookId).get();
        if (foundedBook.getTitle() == null) {
            System.out.printf("Book with ID %s not found%n", bookId);
            return;
        }
        foundedBook.getComment().add(new Comment(bookId, comment));
        bookRepository.save(foundedBook);
        printInfoAboutBook(bookRepository.findById(bookId).get().getTitle());
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        System.out.println("Done");
    }

    @Override
    public void printInfoAboutBook(String title) {
        Book book = bookRepository.findByTitle(title);
        System.out.println("==============");
        System.out.println("ID: " + book.getId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor().getName());
        System.out.println("Genre: " + book.getGenre().getTitle());
        book.getComment().forEach(c -> System.out.println("\nComment :" + c.getText()));
    }
}
