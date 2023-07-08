package ru.otus.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.author.Author;

public interface AuthorRepository extends MongoRepository<Author, Long> {
}
