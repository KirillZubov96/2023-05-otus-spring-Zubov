package ru.otus.dao.comment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.dao.book.BookDao;
import ru.otus.dao.book.BookDaoJbdc;
import ru.otus.domain.book.Book;
import ru.otus.domain.comment.BookComment;

import java.util.List;

@Component
@Data
public class BookCommentDaoJdbc implements BookCommentDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    private BookDao bookDao;
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
    public void insert(int book_id, String comment) {
        em.persist(new BookComment(comment, bookDao.getById(book_id)));
    }

    @Override
    public BookComment getById(int id) {
        return em.find(BookComment.class, id);
    }

    @Override
    public List<BookComment> getAll() {
        return em.createQuery("select b from BookComment b", BookComment.class).getResultList();
    }

    @Override
    public void deleteById(int id) {
        em.remove(getById(id));
    }

    @Override
    public BookComment getByBookId(long book_id) {
        TypedQuery<BookComment> query = em.createQuery("select b from BookComment b where b.book.id = :bookId", BookComment.class);
        query.setParameter("bookId", book_id);
        return query.getSingleResult();
    }

}
