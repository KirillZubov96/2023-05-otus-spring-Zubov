package ru.otus.dao.author;

import ru.otus.domain.author.Author;

public interface AuthorDao {
   long count();

   Author getById(long id);

}
