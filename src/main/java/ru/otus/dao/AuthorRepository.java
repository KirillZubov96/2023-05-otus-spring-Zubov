package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.author.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
