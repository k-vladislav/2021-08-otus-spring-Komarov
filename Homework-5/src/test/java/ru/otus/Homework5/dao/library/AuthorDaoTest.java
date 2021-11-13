package ru.otus.Homework5.dao.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.Homework5.domain.Author;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql({"/test_schema.sql", "/test_data.sql"})
class AuthorDaoTest {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private AuthorDao dao;

    @BeforeEach
    void setUp() {
        dao = new AuthorDao(jdbc);
    }

    @Test
    void count() {
        assertThat(dao.count()).isEqualTo(3);
    }

    @Test
    void insert() {
        dao.insert("AUTHOR_FOUR");
        assertThat(dao.count()).isEqualTo(4);
    }

    @Test
    void update() {
        String oldLastName = "AUTHOR_ONE";
        String newLastName = "AUTHOR_ONE_NEW";
        Optional<Long> idOpt = dao.getId(oldLastName);
        Integer idUpd = idOpt.map(id -> dao.update(id, newLastName)).orElse(0);
        assertThat(idUpd).isNotZero();
        assertThat(dao.getId(oldLastName)).isEmpty();
        assertThat(dao.getId(newLastName)).isPresent();
    }

    @Test
    void getById() {
        String existingAuthor = "AUTHOR_ONE";
        String nonExistingAuthor = "AUTHOR_ZERO";
        assertThat(dao.getId(existingAuthor)).isPresent();
        assertThat(dao.getId(nonExistingAuthor)).isEmpty();
    }

    @Test
    void getAll() {
        List<String> factAuthorsNames = dao.getAll().stream().map(Author::getLastName).collect(Collectors.toList());
        List<String> expectedAuthorsNames = List.of("AUTHOR_ONE", "AUTHOR_TWO", "AUTHOR_THREE");
        assertThat(factAuthorsNames).isEqualTo(expectedAuthorsNames);
    }

    @Test
    void delete() {
        dao.delete(1);
        assertThat(dao.count()).isEqualTo(2);
        assertThat(dao.getId("AUTHOR_ONE")).isEmpty();
    }

    @Test
    void getId() {
        Optional<Long> idOpt = dao.getId("AUTHOR_ONE");
        assertThat(idOpt).isPresent();
        idOpt.ifPresent(id -> assertThat(id).isEqualTo(1));

        assertThat(dao.getId("AUTHOR_ZERO")).isEmpty();
    }
}