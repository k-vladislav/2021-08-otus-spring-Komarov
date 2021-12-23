package ru.otus.homework6.dao_repositories;

import org.springframework.stereotype.Repository;
import ru.otus.homework6.models.Book;

import javax.persistence.*;
import java.util.Optional;

@Repository
public class BooksRepository implements LibraryRepository<Book> {

    @PersistenceContext
    private final EntityManager em;

    protected BooksRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    //todo Как проверять на уровне service, что persist или merge успешны
    public void save(Book entity) {
        //todo Как лучше проверить, что сущность уже в персистенс-контексте: em.contains() или entity.getId()<=0 как в лекции?
        if (!em.contains(entity)) {
            em.persist(entity);
        } else {
            //todo Как при обновлении merge добиться, чтобы операция каскадно не распространялась на дочерние сущности, если стоит CascadeType.Merge
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
    public Optional<Book> findByNameWithAttributes(String value) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-all-attributes-graph");
        //todo При использовании NamedEntityGraph для загрузки дочерних сущностей, нужно ли в JPQL-запросе писать JOIN FETCH?
        // Дочерние сущности подтягиваются без JOIN FETCH, но в лекции используется JOIN FETCH вместе с NamedEntityGraph (возможно для демонстрации)
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
    //todo При удалении (em.remove()) сущности, как удалить связи с дочерними из таблицы связей, но не сами дочерние сущности?
    public void delete(Book entity) {
        em.remove(entity);
    }
}
