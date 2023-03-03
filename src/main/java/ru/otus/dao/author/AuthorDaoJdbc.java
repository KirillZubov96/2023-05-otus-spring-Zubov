package ru.otus.dao.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }
}
