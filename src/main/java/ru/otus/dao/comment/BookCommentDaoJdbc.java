package ru.otus.dao.comment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.domain.comment.BookComment;

import java.util.List;
@Repository
@Transactional
public class BookCommentDaoJdbc implements BookCommentDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public BookCommentDaoJdbc(EntityManager manager) {
        this.em = manager;
    }

    @Override
    public long count() {
        Long count = em.createQuery("select count(*) from BookComment", Long.class).getSingleResult();
        return count == null ? 0 : count;
    }

    @Override
    public void insert(long book_id, String comment) {
        em.createQuery(String.format("insert into BookComment (name, author_id, genre_id) values (%s, %s)", book_id, comment)).executeUpdate();
    }

    @Override
    public BookComment getById(long id) {
        TypedQuery<BookComment> query = em.createQuery("select b from BookComment b where id = :id", BookComment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<BookComment> getAll() {
        return em.createQuery("select b from BookComment b", BookComment.class).getResultList();
    }

    @Override
    public void deleteById(long id) {
        em.createQuery("delete from BookComment where id = " + id).executeUpdate();
    }
}
