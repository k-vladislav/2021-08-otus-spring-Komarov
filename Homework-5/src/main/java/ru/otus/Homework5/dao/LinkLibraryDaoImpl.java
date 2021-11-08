package ru.otus.Homework5.dao;

import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"}) //todo check warns
public class LinkLibraryDaoImpl implements LinkLibraryDao {

    private final NamedParameterJdbcOperations jdbc;

    public LinkLibraryDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public Optional<Long> getIdByValueBookAuthor(Long bookId, Long authorId) {
        Map<String, Long> params = Map.of("bookId", bookId, "authorId", authorId);
        List<Long> ids = jdbc.query("select id from Book_Author where BOOK_ID = :bookId and AUTHOR_ID = :authorId", params, SingleColumnRowMapper.newInstance(Long.class));
        return ids.stream().findFirst();
    }

    @Override
    public void linkBookAuthor(Long bookId, Long authorId) {
        Map<String, Long> params = Map.of("bookId", bookId, "authorId", authorId);
        jdbc.update("insert into Book_Author (`BOOK_ID`,`AUTHOR_ID`) values (:bookId,:authorId)",params);
    }

    @Override
    public Optional<Long> getIdByValueBookGenre(Long bookId, Long genreId) {
        Map<String, Long> params = Map.of("bookId", bookId, "genreId", genreId);
        List<Long> ids = jdbc.query("select id from Book_Genre where BOOK_ID = :bookId and GENRE_ID = :genreId", params, SingleColumnRowMapper.newInstance(Long.class));
        return ids.stream().findFirst();
    }

    @Override
    public void linkBookGenre(Long bookId, Long genreId) {
        Map<String, Long> params = Map.of("bookId", bookId, "genreId", genreId);
        jdbc.update("insert into Book_Genre (`BOOK_ID`,`GENRE_ID`) values (:bookId,:genreId)",params);
    }
}
