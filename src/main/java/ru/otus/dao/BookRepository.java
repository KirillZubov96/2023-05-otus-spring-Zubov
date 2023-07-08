package ru.otus.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.book.Book;

public interface BookRepository extends MongoRepository<Book, Long> {

    Book findByTitle(String title);

    Book findByTitleAndAuthorNameAndGenreTitle(String title, String authorName, String genreTitle);

}
