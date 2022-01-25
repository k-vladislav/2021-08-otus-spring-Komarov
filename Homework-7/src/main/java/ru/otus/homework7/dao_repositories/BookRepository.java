package ru.otus.homework7.dao_repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework7.models.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);

    Optional<Book> findBookById(long id);


    //пробный запуск показал, что int>0 если было что-то удалено (т.е. это кол-во затронутых строк в таблице? для update аналогично)
    int deleteByTitle(String title);

    @Modifying
    @Query("update Book b set b.title = :newTitle where b.title = :oldTitle")
    int updateBookByTitle(@Param("oldTitle") String oldTitle, @Param("newTitle") String newTitle);


    /*
    FETCH: When the javax.persistence.fetchgraph property is used to specify an entity graph,
    attributes that are specified by attribute nodes of the entity graph are treated as FetchType.EAGER
    and attributes that are not specified are treated as FetchType.LAZY

    LOAD: When the javax.persistence.loadgraph property is used to specify an entity graph,
    attributes that are specified by attribute nodes of the entity graph are treated as FetchType.EAGER
    and attributes that are not specified are treated according to their specified or default FetchType.
     */

    @EntityGraph(value = "book-with-comments", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Book> findBookWithCommentsById(long id);

    @EntityGraph(value = "book-with-comments", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Book> findBookWithCommentsByTitle(String title);
}
