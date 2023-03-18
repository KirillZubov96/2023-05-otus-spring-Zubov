package ru.otus.dao.genre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.domain.genre.Genre;

@Repository
@Transactional
public class GenreDaoJbdc implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public GenreDaoJbdc(EntityManager manager) {
        this.em = manager;
    }

    @Override
    public int count() {
        Integer count = em.createQuery("select count(*) from genres").executeUpdate();
        return count == null ? 0 : count;
    }

    @Override
    public Genre getById(long id) {
        return em.createQuery(
                "select id, name from genres where id = " + id, Genre.class).getSingleResult();
    }
}
