package ru.otus.dao.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.dao.author.AuthorDao;
import ru.otus.dao.genre.GenreDao;
import ru.otus.domain.author.Author;
import ru.otus.domain.book.Book;
import ru.otus.domain.genre.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@JdbcTest
@Import(BookDaoJbdc.class)
public class BookDaoJbdcTest {
    private static final int EXPECTED_BOOKS_COUNT = 1;
    private static final String EXPECTED_BOOKS_NAME = "Книга";
    private BookDao bookDao;

    private AuthorDao authorDao;

    private GenreDao genreDao;
    @Autowired
    public BookDaoJbdcTest(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        int actualBooksCount = bookDao.count();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        Author author = authorDao.getById(EXPECTED_BOOKS_COUNT);
        Genre genre = genreDao.getById(EXPECTED_BOOKS_COUNT);
        Book expectedBook = new Book(EXPECTED_BOOKS_NAME, author, genre);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Author author = Mockito.mock(Author.class);
        Genre genre = Mockito.mock(Genre.class);
        Book expectedBook = new Book("Тестовая книга", author, genre);
        bookDao.insert(expectedBook.getName(), author.getId(), genre.getId());
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDao.getById(1))
                .doesNotThrowAnyException();

        bookDao.deleteById(1);

        assertThatThrownBy(() -> bookDao.getById(1))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        Author author = Mockito.mock(Author.class);
        Genre genre = Mockito.mock(Genre.class);
        List<Book> actualBookList = bookDao.getAll();
        List<Book> expectedBookList = new ArrayList<>();
        expectedBookList.add(new Book(EXPECTED_BOOKS_NAME, author, genre));
        assertThat(actualBookList.size() == (expectedBookList.size()));
        assertThat(actualBookList.get(0).getName().equals(expectedBookList.get(0).getName()));
    }
}
