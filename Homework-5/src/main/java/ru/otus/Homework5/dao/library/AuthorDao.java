package ru.otus.Homework5.dao.library;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
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
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
public class AuthorDao implements LibraryDao<Author> {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDao(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(last_name) from Author", Map.of(), Integer.class);
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
    public int update(long id, String newLastName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("newLastName", newLastName);
        return jdbc.update("update Author set last_name = :newLastName where id = :id", params);
    }

    @Override
    public Optional<Author> getById(long id) {
        Author author = jdbc.queryForObject("select last_name from Author where id = :id", Map.of("id", id), new AuthorMapper());
        return Optional.ofNullable(author);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select last_name from Author", new AuthorMapper());
    }

    @Override
    public int delete(long id) {
        return jdbc.update("delete from Author where id = :id", Map.of("id", id));
    }

    @Override
    public Optional<Long> getId(String lastName) {
        try {
            Long id = jdbc.queryForObject("select id from Author where last_name = :lastName", Map.of("lastName", lastName), Long.class);
            return Optional.ofNullable(id);
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            String lastName = rs.getString("last_name");
            return new Author(lastName);
        }
    }
}
