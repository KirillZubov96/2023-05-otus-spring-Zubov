package ru.otus.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.genre.Genre;

public interface GenreRepository extends MongoRepository<Genre, Long> {
}
