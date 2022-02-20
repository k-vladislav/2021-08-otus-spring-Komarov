package ru.otus.homework8.dao_repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework8.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
