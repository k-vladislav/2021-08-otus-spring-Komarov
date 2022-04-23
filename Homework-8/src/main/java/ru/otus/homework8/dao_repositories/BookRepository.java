package ru.otus.homework8.dao_repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework8.models.Book;

public interface BookRepository extends MongoRepository<Book,Integer> {

}
