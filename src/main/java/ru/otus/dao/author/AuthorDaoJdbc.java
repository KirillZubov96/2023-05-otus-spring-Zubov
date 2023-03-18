package ru.otus.dao.author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.domain.author.Author;

@Repository
@Transactional
public class AuthorDaoJdbc implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public AuthorDaoJdbc(EntityManager manager) {
        this.em = manager;
    }

    @Override
    public long count() {
        Long count = em.createQuery("select count(*) from Author", Long.class).getSingleResult();
        return count == null ? 0 : count;
    }

    @Override
    public Author getById(long id) {
        return em.createQuery(
                "select id, name from Author where id = " + id, Author.class).getSingleResult();
    }
}
