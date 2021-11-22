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
        assertThat(dao.getById(1L)).hasValueSatisfying(book -> "BOOK_ONE".equals(book.getTitle()));
        assertThat(dao.getById(5L)).isEmpty();
    }

    @Test
    void getByListOfId() {
        List<Long> allExistIds = List.of(1L, 2L, 3L);
        List<Long> partlyExistIds = List.of(1L, 2L, 4L);
        List<Long> allNonExistIds = List.of(4L, 5L, 6L);

        assertThat(dao.getByListOfId(allExistIds))
                .anyMatch(book -> "BOOK_ONE".equals(book.getTitle()))
                .anyMatch(book -> "BOOK_TWO".equals(book.getTitle()))
                .anyMatch(book -> "BOOK_THREE".equals(book.getTitle()));

        assertThat(dao.getByListOfId(partlyExistIds)).hasSize(2);

        assertThat(dao.getByListOfId(allNonExistIds)).isEmpty();
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