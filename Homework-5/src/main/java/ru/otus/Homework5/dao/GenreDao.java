package ru.otus.Homework5.dao;

import org.springframework.jdbc.core.RowMapper;
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

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"}) //todo check warns
public class GenreDao implements Dao<Genre> {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDao(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from Genre", Map.of(), Integer.class);
    }

    @Override
    public long insert(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("genre", genre.getGenre());
        jdbc.update("insert into Genre (`GENRE`) values (:genre)", params,keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Genre getById(long id) {
        return jdbc.queryForObject("select * from Genre where id = :id", Map.of("id", id), new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from Genre", new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from Genre where id = :id", Map.of("id", id));
    }

    @Override
    public Long getIdByValue(String value) {
        return jdbc.queryForObject("select id from Genre where genre=:genre",Map.of("genre",value),Long.class);
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            String genre = rs.getString("genre");
            return new Genre(genre);
        }
    }
}
