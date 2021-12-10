package ru.otus.homework6.dao_repositories;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository<T> {


    /**
     * EntityManager.persist (CREATE)
     *
     * @param entity entity
     */
    void insert(T entity);

    /**
     * READ
     *
     * @param fieldValue String value of field (e.g. title)
     * @return List of entities (e.g. books)
     */
    Optional<T> findByValue(String fieldValue);


    /**
     * UPDATE
     *
     * @param entity new entity
     * @return number of rows affected //todo
     */
    T mergeUpdate(T entity);


    /**
     * DELETE (REMOVE)
     *
     * @param entity entity
     */
    void delete(T entity);


    /**
     * To check result of persist, delete (and merge?)
     *
     * @param entity
     * @return true if entity is in persistence context
     */
    boolean contains(T entity);


}
