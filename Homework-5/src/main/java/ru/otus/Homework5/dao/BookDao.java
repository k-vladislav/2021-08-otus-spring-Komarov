package ru.otus.Homework5.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.Homework5.domain.Book;

import java.sql.PreparedStatement;
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
    public long insert(String title) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", title);
        jdbc.update("insert into Book (`TITLE`) values (:title)", params, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void delete(String title) {
        jdbc.update("delete from Book where title = :title", Map.of("title", title));
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
    public Optional<Long> getId(String title) {
        List<Long> ids = jdbc.query("select id from Book where title = :title", Map.of("title", title), SingleColumnRowMapper.newInstance(Long.class));
        return ids.stream().findFirst();

    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            String title = rs.getString("title");
            return new Book(title);
        }
    }

}
