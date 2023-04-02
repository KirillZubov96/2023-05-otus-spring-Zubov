package ru.otus.dao.book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.domain.book.Book;

import java.util.List;

@Component
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
    public void insert(String name, int authorId, int genreId) {
        insertWithEntityManager(new Book(name, authorId, genreId));
    }

    @Override
    public Book getById(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public void deleteById(int id) {
        em.remove(getById(id));
    }

    @Transactional
    private void insertWithEntityManager(Book book) {
        this.em.persist(book);
    }
}
