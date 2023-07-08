package ru.otus.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.comment.Comment;

public interface CommentRepository extends MongoRepository<Comment, Long> {
}
