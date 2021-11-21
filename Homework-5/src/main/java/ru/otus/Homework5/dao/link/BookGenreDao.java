package ru.otus.Homework5.dao.link;

import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.Homework5.domain.Genre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
public class BookGenreDao implements LinkingDao<Genre> {

    private final NamedParameterJdbcOperations jdbc;

    public BookGenreDao(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public long link(long bookId, long genreId) {
        Map<String, Long> params = Map.of("bookId", bookId, "genreId", genreId);
        return jdbc.update("insert into Book_Genre (BOOK_ID,GENRE_ID) values (:bookId, :genreId)", params);
    }

    @Override
    public Optional<Long> getId(long bookId, long genreId) {
        Map<String, Long> params = Map.of("bookId", bookId, "genreId", genreId);
        List<Long> ids = jdbc.query("select id from Book_Genre where BOOK_ID = :bookId and GENRE_ID = :genreId", params, SingleColumnRowMapper.newInstance(Long.class));
        return ids.stream().findFirst();
    }

    @Override
    public List<Long> getListOfIdByBookId(long bookId) {
        Map<String, Long> params = Map.of("bookId", bookId);
        return jdbc.queryForList("select GENRE_ID from Book_Genre where BOOK_ID = :bookId", params, Long.class);
    }

    @Override
    public List<Long> getListOfBookIdById(long genreId) {
        Map<String, Long> params = Map.of("genreId", genreId);
        return jdbc.queryForList("select BOOK_ID from Book_Genre where GENRE_ID = :genreId", params, Long.class);
    }

    @Override
    public int deleteByBookId(long bookId) {
        Map<String, Long> params = Map.of("bookId", bookId);
        return jdbc.update("delete from Book_Genre where BOOK_ID = :bookId", params);
    }

    @Override
    public int deleteById(long genreId) {
        Map<String, Long> params = Map.of("genreId", genreId);
        return jdbc.update("delete from Book_Genre where GENRE_ID = :genreId", params);
    }

    @Override
    public int delete(long bookId, long genreId) {
        Map<String, Long> params = Map.of("bookId", bookId, "genreId", genreId);
        return jdbc.update("delete from Book_Genre where  BOOK_ID = :bookId and AUTHOR_ID = :genreId", params);
    }
}
