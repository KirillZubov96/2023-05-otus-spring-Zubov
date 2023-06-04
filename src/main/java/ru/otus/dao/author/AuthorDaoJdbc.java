package ru.otus.dao.author;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.otus.domain.author.Author;

import java.util.Optional;

@Component
public interface AuthorDaoJdbc extends CrudRepository<Author, Integer> {
    Optional<Author> findById(int id);

    Optional<Author> findByName(String s);
}
