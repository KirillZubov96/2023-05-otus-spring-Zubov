package ru.otus.dao.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.author.AuthorDaoJdbc;
import ru.otus.domain.author.Author;
import ru.otus.domain.genre.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class GenreDaoJbdc implements GenreDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public GenreDaoJbdc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from genres", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from genres where id = :id", params, new GenreDaoJbdc.GenreMapper()
        );
    }

    private static  class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre (id, name);
        }
    }
}
