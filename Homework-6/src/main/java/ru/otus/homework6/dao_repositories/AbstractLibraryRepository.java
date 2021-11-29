package ru.otus.homework6.dao_repositories;

import javax.persistence.EntityManager;

public abstract class AbstractLibraryRepository<T> implements LibraryRepository<T> {

    private final EntityManager em;

    protected AbstractLibraryRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public void insert(T entity) {
        em.persist(entity);
    }

    @Override
    public T mergeUpdate(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

}
