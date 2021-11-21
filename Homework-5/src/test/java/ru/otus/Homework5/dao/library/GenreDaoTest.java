package ru.otus.Homework5.dao.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.Homework5.domain.Genre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql({"/test_schema.sql", "/test_data.sql"})
class GenreDaoTest {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private GenreDao dao;

    @BeforeEach
    void setUp() {
        dao = new GenreDao(jdbc);
    }

    @Test
    void count() {
        assertThat(dao.count()).isEqualTo(3);
    }

    @Test
    void insert() {
        dao.insert("GENRE_FOUR");
        assertThat(dao.count()).isEqualTo(4);
    }

    @Test
    void update() {
        String oldGenre = "GENRE_ONE";
        String newGenre = "GENRE_ONE_NEW";
        Optional<Long> idOpt = dao.getId(oldGenre);
        Integer idUpd = idOpt.map(id -> dao.update(id, newGenre)).orElse(0);
        assertThat(idUpd).isNotZero();
        assertThat(dao.getId(oldGenre)).isEmpty();
        assertThat(dao.getId(newGenre)).isPresent();
    }

    @Test
    void delete() {
        dao.delete(1);
        assertThat(dao.count()).isEqualTo(2);
        assertThat(dao.getId("GENRE_ONE")).isEmpty();
    }

    @Test
    void getById() {
        assertThat(dao.getById(1L)).hasValueSatisfying(genre -> "GENRE_ONE".equals(genre.getGenre()));
        assertThat(dao.getById(5L)).isEmpty();
    }

    @Test
    void getByListOfId() {
        List<Long> allExistIds = List.of(1L, 2L, 3L);
        List<Long> partlyExistIds = List.of(1L, 2L, 4L);
        List<Long> allNonExistIds = List.of(4L, 5L, 6L);

        assertThat(dao.getByListOfId(allExistIds))
                .anyMatch(genre -> "GENRE_ONE".equals(genre.getGenre()))
                .anyMatch(genre -> "GENRE_TWO".equals(genre.getGenre()))
                .anyMatch(genre -> "GENRE_THREE".equals(genre.getGenre()));

        assertThat(dao.getByListOfId(partlyExistIds)).hasSize(2);

        assertThat(dao.getByListOfId(allNonExistIds)).isEmpty();
    }

    @Test
    void getAll() {
        List<String> factGenres = dao.getAll().stream().map(Genre::getGenre).collect(Collectors.toList());
        List<String> expectedGenres = List.of("GENRE_ONE", "GENRE_TWO", "GENRE_THREE");
        assertThat(factGenres).isEqualTo(expectedGenres);
    }

    @Test
    void getId() {
        Optional<Long> idOpt = dao.getId("GENRE_ONE");
        assertThat(idOpt).isPresent();
        idOpt.ifPresent(id -> assertThat(id).isEqualTo(1));

        assertThat(dao.getId("GENRE_ZERO")).isEmpty();
    }
}