package ru.otus.dao.book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.domain.book.Book;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class BookDaoJbdc implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public BookDaoJbdc(EntityManager manager) {
        this.em = manager;
    }

    @Override
    public long count() {
        Long count = em.createQuery("select count(*) from Book", Long.class).getSingleResult();
        return count == null ? 0 : count;
    }

    @Override
    public void insert(String name, long authorId, long genreId) {
        em.createQuery(String.format("insert into Book (name, author_id, genre_id) values (%s, %s, %s)", name, authorId, genreId)).executeUpdate();
    }

    @Override
    public Book getById(long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where id = :id", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public void deleteById(long id) {
        em.createQuery("delete from Book where id = " + id).executeUpdate();
    }
}
