package ru.otus.dao.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDaoJbdc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public GenreDaoJbdc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }
}
