package ru.otus.homework6.dao_repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface probier<T, ID> extends CrudRepository<T,ID> {
    @Override
    default <S extends T> S save(S entity) {
        return null;
    }

    @Override
    default <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    default Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(ID id) {
        return false;
    }

    @Override
    default Iterable<T> findAll() {
        return null;
    }

    @Override
    default Iterable<T> findAllById(Iterable<ID> ids) {
        return null;
    }

    @Override
    default long count() {
        return 0;
    }

    @Override
    default void deleteById(ID id) {

    }

    @Override
    default void delete(T entity) {

    }

    @Override
    default void deleteAllById(Iterable<? extends ID> ids) {

    }

    @Override
    default void deleteAll(Iterable<? extends T> entities) {

    }

    @Override
    default void deleteAll() {

    }
}
