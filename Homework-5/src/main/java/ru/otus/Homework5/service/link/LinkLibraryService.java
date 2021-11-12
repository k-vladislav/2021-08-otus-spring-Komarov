package ru.otus.Homework5.service.link;

import java.util.List;
import java.util.Optional;

public interface LinkLibraryService<T> {

    long link(long bookId, long id);

    Optional<Long> getId(long bookId, long id);

    List<Long> getListOfIdByBookId(long bookId);

    List<Long> getListOfBookIdById(long id);

    int deleteByBookId(long bookId);

    int deleteById(long id);

    int delete(long bookId, long id);
}
