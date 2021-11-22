package ru.otus.Homework5.dao.link;

import java.util.List;
import java.util.Optional;

/**
 * Interface for Book_Author and Book_Genre linking tables
 */
public interface LinkingDao<T> {

    /**
     * Link id (of author or genre) to book (by inserting their IDs to table)
     *
     * @param bookId id of a book
     * @param id     id of author or genre etc.
     * @return assumed 1 that row was added
     */
    long link(long bookId, long id);


    /**
     * Get number of rows by (id,bookId) (to avoid inserting duplicates)
     *
     * @param bookId id of a book
     * @param id id of author or genre etc
     * @return optional long, isPresent if exists
     */
    Optional<Long> getId(long bookId, long id);

    /**
     * Get List Of IDs by Book ID
     *
     * @param bookId id of book
     * @return Optional list of found ids of genres or authors or etc.
     */
    List<Long> getListOfIdByBookId(long bookId);

    /**
     * Get List of Books ID by ID of author or genre etc
     *
     * @param id id of author or genre or etc.
     * @return Optional List of book IDs
     */
    List<Long> getListOfBookIdById(long id);


    /**
     * Delete all rows by bookId
     *
     * @param bookId id of a book
     * @return assumed number of affected rows?
     */
    int deleteByBookId(long bookId);

    /**
     * Delete all rows by id of author or genre etc
     *
     * @param Id id of a book
     * @return assumed number of affected rows?
     */
    int deleteById(long Id);


    /**
     * Deletes row...
     *
     * @param bookId bookId
     * @param id id of author or genre
     * @return ??? number of affected rows
     */
    int delete(long bookId, long id);

}
