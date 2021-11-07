package ru.otus.Homework5.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.Homework5.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"}) //todo check warns
public class BookDao implements LibraryDao<Book> {

    private final NamedParameterJdbcOperations jdbc;

    public BookDao(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from Book", Map.of(), Integer.class);
    }

    @Override
    public long insert(String value) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", value);
        jdbc.update("insert into Book (`TITLE`) values (:title)", params,keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Book> getById(long id) {
        Book book = jdbc.queryForObject("select * from Book where id = :id", Map.of("id", id), new BookMapper());
        return Optional.ofNullable(book);
    }

    @Override
    public Optional<List<Book>> getAll() {
        List<Book> books = jdbc.query("select * from Book", new BookMapper());
        return Optional.ofNullable(books);
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from Book where id = :id", Map.of("id", id));
    }

    @Override
    public Optional<Long> getIdByValue(String value) {
        Long id = jdbc.queryForObject("select id from Book where title=:title", Map.of("title", value), Long.class);
        return Optional.ofNullable(id);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            String title = rs.getString("title");
            return new Book(title);
        }
    }
}
