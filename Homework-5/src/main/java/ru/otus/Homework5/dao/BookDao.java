package ru.otus.Homework5.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.Homework5.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"}) //todo check warns
public class BookDao implements Dao<Book> {

    private final NamedParameterJdbcOperations jdbc;

    public BookDao(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from Book", Map.of(), Integer.class);
    }

    @Override
    public void insert(Book Book) {
        jdbc.update("insert into Book (`TITLE`) values (:lastName)", Map.of("title", Book.getTitle()));
    }

    @Override
    public Book getById(long id) {
        return jdbc.queryForObject("select * from Book where id = :id", Map.of("id", id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from Book", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from Book where id = :id", Map.of("id", id));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            String title = rs.getString("title");
            return new Book(title);
        }
    }
}
