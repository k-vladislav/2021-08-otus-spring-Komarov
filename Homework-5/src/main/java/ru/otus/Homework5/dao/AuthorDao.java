package ru.otus.Homework5.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.Homework5.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"}) //todo check warns
public class AuthorDao implements Dao<Author> {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDao(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from Author", Map.of(), Integer.class);
    }

    @Override
    public long insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("lastName", author.getLastName());
        jdbc.update("insert into Author (`LAST_NAME`) values (:lastName)",params,keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Author getById(long id) {
        return jdbc.queryForObject("select * from Author where id = :id", Map.of("id", id), new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from Author", new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from Author where id = :id", Map.of("id", id));
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            String lastName = rs.getString("last_name");
            return new Author(lastName);
        }
    }
}
