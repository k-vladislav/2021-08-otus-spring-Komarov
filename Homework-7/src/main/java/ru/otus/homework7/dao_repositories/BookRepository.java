package ru.otus.homework7.dao_repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework7.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
