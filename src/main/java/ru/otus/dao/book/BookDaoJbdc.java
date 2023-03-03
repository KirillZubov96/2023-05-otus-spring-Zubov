package ru.otus.dao.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.author.Author;
import ru.otus.domain.book.Book;
import ru.otus.domain.genre.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJbdc implements BookDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public BookDaoJbdc(NamedParameterJdbcOperations jdbc) {
        this.namedParameterJdbcOperations = jdbc;
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from books", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into books (id, name, author, genre) values (:id, :name, :author, :genre)",
                Map.of("id", book.getId(), "name", book.getName(), "author", book.getAuthor(), "genre", book.getGenre()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, author, genre from books where id = :id", params, new BookDaoJbdc.BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, name, author, genre from books", new BookDaoJbdc.BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Author author = resultSet.getObject("author", Author.class);
            Genre genre = resultSet.getObject("genre", Genre.class);
            return new Book(id, name, author, genre);
        }
    }
}
