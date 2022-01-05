package ru.otus.homework7.dao_repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LibraryRepository<T> {

    /**
     * @return set of all T (books)
     */
    List<T> getAll();

    /**
     * Persists or merges
     *
     * @param entity entity
     */
    void save(T entity);

    /**
     * READ
     *
     * @param value String value of field (e.g. title)
     * @return List of entities (e.g. books)
     */
    Optional<T> findByName(String value);

    /**
     * Load entity (book) with all attributes (Named Entity Graph)
     *
     * @param value Book title
     * @return optional of entity (Book)
     */
    Optional<T> findByNameWithComments(String value);


    /**
     * DELETE (REMOVE)
     *
     * @param entity entity
     */
    void delete(T entity);


}
