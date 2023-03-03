package ru.otus.dao.author;

import ru.otus.domain.author.Author;

import java.util.List;

public interface AuthorDao {
    int count();

    void insert(Author person);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
