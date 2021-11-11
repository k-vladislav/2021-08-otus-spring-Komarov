package ru.otus.Homework5.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.Homework5.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"}) //todo check warns
public class AuthorDao implements LibraryDao<Author> {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDao(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from Author", Map.of(), Integer.class);
    }

    @Override
    public long insert(String lastName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("lastName", lastName);
        jdbc.update("insert into Author (`LAST_NAME`) values (:lastName)", params, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Author> getById(long id) {
        Author author = jdbc.queryForObject("select * from Author where id = :id", Map.of("id", id), new AuthorMapper());
        return Optional.ofNullable(author);
    }

    @Override
    public Optional<List<Author>> getAll() {
        List<Author> authors = jdbc.query("select * from Author", new AuthorMapper());
        return Optional.ofNullable(authors);
    }

    @Override
    public void delete(String lastName) {
        jdbc.update("delete from Author where last_name = :value", Map.of("id", lastName));
    }

    @Override
    public Optional<Long> getId(String lastName) {
        List<Long> ids = jdbc.query("select id from Author where last_name = :lastName", Map.of("lastName", lastName), SingleColumnRowMapper.newInstance(Long.class));
        return ids.stream().findFirst();
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            String lastName = rs.getString("last_name");
            return new Author(lastName);
        }
    }
}
