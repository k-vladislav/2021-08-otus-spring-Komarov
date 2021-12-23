package ru.otus.homework6.dao_repositories;

import java.util.Optional;

public interface LibraryRepository<T> {

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
    Optional<T> findByNameWithAttributes(String value);


    /**
     * DELETE (REMOVE)
     *
     * @param entity entity
     */
    void delete(T entity);


}
