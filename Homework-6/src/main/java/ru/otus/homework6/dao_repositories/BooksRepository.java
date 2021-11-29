package ru.otus.homework6.dao_repositories;

import org.springframework.stereotype.Repository;
import ru.otus.homework6.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BooksRepository extends AbstractLibraryRepository<Book> {

    @PersistenceContext
    private final EntityManager em;

    protected BooksRepository(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    public Optional<Book> findByValue(String fieldValue) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", fieldValue);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


}
