package ru.otus.Homework5.dao.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.Homework5.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql({"/test_schema.sql", "/test_data.sql"})
class BookDaoTest {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private BookDao dao;

    @BeforeEach
    void setUp() {
        dao = new BookDao(jdbc);
    }


    @Test
    void count() {
        assertThat(dao.count()).isEqualTo(3);
    }

    @Test
    void insert() {
        dao.insert("BOOK_FOUR");
        assertThat(dao.count()).isEqualTo(4);
    }

    @Test
    void update() {
        String oldTitle = "BOOK_ONE";
        String newTitle = "BOOK_ONE_NEW";
        Optional<Long> idOpt = dao.getId(oldTitle);
        Integer idUpd = idOpt.map(id -> dao.update(id, newTitle)).orElse(0);
        assertThat(idUpd).isNotZero();
        assertThat(dao.getId(oldTitle)).isEmpty();
        assertThat(dao.getId(newTitle)).isPresent();
    }

    @Test
    void delete() {
        dao.delete(1);
        assertThat(dao.count()).isEqualTo(2);
        assertThat(dao.getId("BOOK_ONE")).isEmpty();
    }

    @Test
    void getById() {
        String existingBook = "BOOK_ONE";
        String nonExistingBook = "BOOK_ZERO";
        assertThat(dao.getId(existingBook)).isPresent();
        assertThat(dao.getId(nonExistingBook)).isEmpty();
    }

    @Test
    void getAll() {
        List<String> factBooks = dao.getAll().stream().map(Book::getTitle).collect(Collectors.toList());
        List<String> expectedBook = List.of("BOOK_ONE", "BOOK_TWO", "BOOK_THREE");
        assertThat(factBooks).isEqualTo(expectedBook);
    }

    @Test
    void getId() {
        Optional<Long> idOpt = dao.getId("BOOK_ONE");
        assertThat(idOpt).isPresent();
        idOpt.ifPresent(id -> assertThat(id).isEqualTo(1));

        assertThat(dao.getId("BOOK_ZERO")).isEmpty();
    }
}