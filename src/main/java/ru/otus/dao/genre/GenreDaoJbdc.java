package ru.otus.dao.genre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.domain.genre.Genre;

@Component
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
        return em.find(Genre.class, id);
    }
}
