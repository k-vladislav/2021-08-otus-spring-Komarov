package ru.otus.Homework5.dao.library;

import java.util.List;
import java.util.Optional;

public interface LibraryDao<T> {

    /**
     * @param value value
     * @return id of inserted row
     */
    long insert(String value);

    /**
     * @param id id of row to update
     * @param newValue new value
     * @return number of rows affected
     */
    int update(long id, String newValue);

    /**
     * @param id id of row to delete
     * @return number of rows affected
     */
    int delete(long id);

    /**
     * @return number of all rows in table
     */
    int count();

    /**
     * @deprecated Use getByListOfId(List id) instead
     * @param id id of row
     * @return Optional of T object
     */
    @Deprecated
    Optional<T> getById(long id);


    /**
     * Batch get List of entities
     *
     * @param idList list of Ids
     * @return Optional List of entities
     */
    List<T>  getByListOfId(List<Long> idList);

    /**
     * @return list of T objects
     */
    List<T> getAll();

    /**
     * @param value value
     * @return Optional of id
     */
    Optional<Long> getId(String value);

}
