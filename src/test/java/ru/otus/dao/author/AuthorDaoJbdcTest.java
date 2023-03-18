package ru.otus.dao.author;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.author.Author;
import ru.otus.domain.genre.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({AuthorDaoJdbc.class})
public class AuthorDaoJbdcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 1;
    private static final String EXPECTED_AUTHOR_NAME = "Автор";
    @Autowired
    private AuthorDao authorDao;

    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    void shouldReturnExpectedAuthorsCount() {
    //    int actualGenresCount = authorDao.count();
    //    assertThat(actualGenresCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorsById() {
   //     Author expectedAuthor = new Author(EXPECTED_AUTHORS_COUNT, EXPECTED_AUTHOR_NAME);
   //     Author actualAuthor = authorDao.getById(expectedAuthor.getId());
   //     assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}
