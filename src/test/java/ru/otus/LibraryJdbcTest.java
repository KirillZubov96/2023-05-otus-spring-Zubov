package ru.otus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.dao.LibraryDao;
import ru.otus.domain.book.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class LibraryJdbcTest {
    @Autowired
    LibraryDao libraryDao;

    @DisplayName("Добавление новой книги")
    @Test
    void addNewBookTest() {
        Book book = Mockito.mock(Book.class);
        libraryDao.storeBook(book);
        assertThat(libraryDao.getAllBooks().size()==2);
    }

    @DisplayName("Удаление книги")
    @Test
    void deleteBookTest() {
        libraryDao.removeBookById(Long.valueOf(1));
        assertThat(libraryDao.getAllBooks().size()==1);
    }

    @DisplayName("Получение книги по Id")
    @Test
    void shouldReturnExpectedAuthorsCount() {
        Book book = libraryDao.getBookByID(Long.valueOf(1));
        assertThat(book.getTitle().equals("book1"));
    }
}
