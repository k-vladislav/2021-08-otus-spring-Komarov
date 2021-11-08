package ru.otus.Homework5.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.Homework5.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"}) //todo check warns
public class GenreDao implements LibraryDao<Genre> {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDao(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from Genre", Map.of(), Integer.class);
    }

    @Override
    public long insert(String value) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("genre", value);
        jdbc.update("insert into Genre (`GENRE`) values (:genre)", params, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Genre> getById(long id) {
        Genre genre = jdbc.queryForObject("select * from Genre where id = :id", Map.of("id", id), new GenreMapper());
        return Optional.ofNullable(genre);
    }

    @Override
    public Optional<List<Genre>> getAll() {
        List<Genre> genres = jdbc.query("select * from Genre", new GenreMapper());
        return Optional.ofNullable(genres);
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from Genre where id = :id", Map.of("id", id));
    }

    @Override
    public Optional<Long> getIdByValue(String value) {
        List<Long> ids = jdbc.query("select id from Genre where genre = :genre", Map.of("genre", value), SingleColumnRowMapper.newInstance(Long.class));
        return ids.stream().findFirst();
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            String genre = rs.getString("genre");
            return new Genre(genre);
        }
    }
}