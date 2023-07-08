package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
