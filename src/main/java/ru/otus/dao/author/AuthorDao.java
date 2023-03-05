package ru.otus.dao.author;

import ru.otus.domain.author.Author;

public interface AuthorDao {
    int count();

    Author getById(long id);

}
