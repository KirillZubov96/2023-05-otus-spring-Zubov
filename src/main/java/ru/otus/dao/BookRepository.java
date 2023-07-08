package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.book.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    Book findByTitleAndAuthorNameAndGenreTitle(String title, String authorName, String genreTitle);

}
