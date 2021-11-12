package ru.otus.Homework5.dao.link;

import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.Homework5.domain.Author;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"}) //todo check warns
public class BookAuthorDao implements BookLinksDao<Author> {

    private final NamedParameterJdbcOperations jdbc;

    public BookAuthorDao(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long link(long bookId, long authorId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("bookId", bookId).addValue("authorId", authorId);
        jdbc.update("insert into Book_Author (BOOK_ID,AUTHOR_ID) values (:bookid, :authorId)", param, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Long> getId(long bookId, long authorId) {
        Map<String, Long> params = Map.of("bookId", bookId, "authorId", authorId);
        List<Long> ids = jdbc.query("select id from Book_Author where BOOK_ID = :bookId and AUTHOR_ID = :authorId", params, SingleColumnRowMapper.newInstance(Long.class));
        return ids.stream().findFirst();
    }

    @Override
    public List<Long> getListOfIdByBookId(long bookId) {
        Map<String, Long> params = Map.of("bookId", bookId);
        return jdbc.queryForList("select AUTHOR_ID from Book_Author where BOOK_ID = :bookId", params, Long.class);
    }

    @Override
    public List<Long> getListOfBookIdById(long authorId) {
        Map<String, Long> params = Map.of("authorId", authorId);
        return jdbc.queryForList("select BOOK_ID from Book_Author where AUTHOR_ID = :authorId", params, Long.class);
    }

    @Override
    public int deleteByBookId(long bookId) {
        Map<String, Long> params = Map.of("bookId", bookId);
        return jdbc.update("delete from Book_Author where BOOK_ID = :bookId", params);
    }

    @Override
    public int deleteById(long authorId) {
        Map<String, Long> params = Map.of("authorId", authorId);
        return jdbc.update("delete from Book_Author where AUTHOR_ID = :authorId", params);
    }

    @Override
    public int delete(long bookId, long authorId) {
        Map<String, Long> params = Map.of("bookId", bookId, "authorId", authorId);
        return jdbc.update("delete from Book_Author where  BOOK_ID = :bookId and AUTHOR_ID = :authorId", params);
    }
}
