package ru.otus.dao.genre;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.genre.Genre;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(GenreDaoJbdc.class)
public class GenreDaoJbdcTest {
    private static final int EXPECTED_GENRES_COUNT = 1;
    private static final String EXPECTED_GENRE_NAME = "Жанр";

    @Autowired
    private GenreDao genreDao;

    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenreCount() {
        int actualGenresCount = genreDao.count();
        assertThat(actualGenresCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXPECTED_GENRES_COUNT, EXPECTED_GENRE_NAME);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}
