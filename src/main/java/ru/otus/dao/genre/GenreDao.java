package ru.otus.dao.genre;

import ru.otus.domain.genre.Genre;

public interface GenreDao {
    int count();

    Genre getById(long id);
}
