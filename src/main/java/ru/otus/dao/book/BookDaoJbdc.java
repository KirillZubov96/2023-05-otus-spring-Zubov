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
    public void insert(String name, long authorId, long genreId) {
        namedParameterJdbcOperations.update("insert into books (name, author_id, genre_id) values (:name, :author_id, :genre_id)",
                Map.of("name", name, "author_id", authorId, "genre_id", genreId));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select books.id, books.name, books.author_id, books.genre_id, authors.name as author, genres.name as genre from books" +
                        " inner join authors on books.author_id = authors.id " +
                        "inner join genres on books.genre_id = genres.id where books.id = :id", params, new BookDaoJbdc.BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select books.id, books.name, books.author_id, books.genre_id, authors.name as author, genres.name as genre from books"
                + " inner join authors on books.author_id = authors.id " +
                "inner join genres on books.genre_id = genres.id", new BookDaoJbdc.BookMapper());
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
            Long authorId = resultSet.getLong("author_id");
            Long genreId = resultSet.getLong("genre_id");
            String authorName = resultSet.getString("author");
            String genreName = resultSet.getString("genre");
            return new Book(id, name, new Author(authorId, authorName), new Genre(genreId, genreName));
        }
    }
}
