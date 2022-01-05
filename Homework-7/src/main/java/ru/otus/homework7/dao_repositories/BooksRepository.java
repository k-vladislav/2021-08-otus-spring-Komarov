package ru.otus.homework7.dao_repositories;

import org.springframework.stereotype.Repository;
import ru.otus.homework7.models.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository //todo Repository annotations used with PersistenceExceptionTranslationPostProcessor
public class BooksRepository implements LibraryRepository<Book> {

    @PersistenceContext
    private final EntityManager em;

    protected BooksRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void save(Book entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    public Optional<Book> findByName(String value) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", value);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> findByNameWithComments(String value) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-all-attributes-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", value);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Book entity) {
        em.remove(entity);
    }
}
