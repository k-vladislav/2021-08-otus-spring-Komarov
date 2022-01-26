package ru.otus.homework7.dao_repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.models.Book;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class BookRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepository booksRep;

    @Test
    void findBookByTitle() {
        em.persist(new Book("BOOK"));
        Optional<Book> validBook = booksRep.findBookByTitle("BOOK");
        Optional<Book> invalidBook = booksRep.findBookByTitle("WRONG_TITLE");
        assertThat(validBook)
                .isPresent()
                .get().hasFieldOrPropertyWithValue("title", "BOOK");

        assertThat(invalidBook).isEmpty();
    }

    @Test
    void deleteByTitle() {
        em.persist(new Book("BOOK"));
        int deleted = booksRep.deleteByTitle("BOOK");
        int notDeleted = booksRep.deleteByTitle("WRONG_TITLE");

        assertThat(deleted).isGreaterThan(0);
        assertThat(notDeleted).isEqualTo(0);
    }

    @Test
    void updateBookByTitle() {
        em.persist(new Book("BOOK"));
        int i = booksRep.updateBookByTitle("BOOK", "BUCH");

        assertThat(i).isEqualTo(1);
    }

}