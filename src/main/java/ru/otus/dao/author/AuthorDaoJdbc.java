package ru.otus.dao.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.author.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.namedParameterJdbcOperations = jdbc;
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from authors", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from authors where id = :id", params, new AuthorMapper()
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
