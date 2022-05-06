package ru.otus.homework8.dao_repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework8.models.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Integer>
{
    List<Book> findBooksByTitle(String title);
}
