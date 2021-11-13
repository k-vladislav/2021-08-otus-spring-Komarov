package ru.otus.Homework5.service.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.Homework5.dao.library.LibraryDao;
import ru.otus.Homework5.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = BookService.class)
@TestComponent
class BookServiceTest {
    @MockBean
    LibraryDao<Book> bookDao;
    @Autowired
    LibraryService<Book> bookService;

    @Test
    void update() {
        when(bookDao.getById(1L))
                .thenReturn(Optional.of(new Book("BOOK_ONE")))
                .thenReturn(Optional.of(new Book("BOOK_ONE_UPDATED")));
        when(bookDao.update(1L, "BOOK_ONE_UPDATED")).thenReturn(1);

        Book book = bookService.getById(1L).orElse(new Book("NULL_BOOK"));
        assertThat(book.getTitle()).isEqualTo("BOOK_ONE");

        int update = bookService.update(1L, "BOOK_ONE_UPDATED");
        assertThat(update).isEqualTo(1);

        book = bookService.getById(1L).orElse(new Book("NULL_BOOK"));
        assertThat(book.getTitle()).isEqualTo("BOOK_ONE_UPDATED");

        verify(bookDao, times(2)).getById(anyLong());
        verify(bookDao, times(1)).update(anyLong(), anyString());
    }

    @Test
    void count() {
        when(bookDao.count()).thenReturn(3);
        assertThat(bookService.count()).isEqualTo(3);
    }

    @Test
    void getById() {
        when(bookDao.getById(1L)).thenReturn(Optional.of(new Book("BOOK_ONE")));
        assertThat(bookService.getById(1L)).contains(new Book("BOOK_ONE"));
    }

    @Test
    void getAll() {
        List<Book> books = List.of(new Book("BOOK_ONE")
                , new Book("BOOK_TOW")
                , new Book("BOOK_THREE"));
        when(bookDao.getAll()).thenReturn(books);
        assertThat(bookService.getAll()).containsAll(books);
        verify(bookDao, times(1)).getAll();
    }

    @Test
    void delete() {
        when(bookDao.getId("BOOK_ONE"))
                .thenReturn(Optional.of(1L))
                .thenReturn(Optional.empty());
        Optional<Long> idBeforeDelete = bookService.getId("BOOK_ONE");
        bookService.delete(1L);
        Optional<Long> idAfterDelete = bookService.getId("BOOK_ONE");

        assertThat(idBeforeDelete).contains(1L);
        assertThat(idAfterDelete).isEmpty();
    }

    @Test
    void add() {
        when(bookDao.insert(anyString())).thenReturn(4L);
        long id = bookService.add("BOOK_FOUR");
        assertThat(id).isEqualTo(4L);
    }

    @Test
    void getId() {
        when(bookDao.getId("BOOK_ONE")).thenReturn(Optional.of(1L));
        assertThat(bookService.getId("BOOK_ONE")).contains(1L);
    }
}