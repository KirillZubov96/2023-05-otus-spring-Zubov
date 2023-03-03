package ru.otus.dao.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoJbdc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public BookDaoJbdc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }
}
