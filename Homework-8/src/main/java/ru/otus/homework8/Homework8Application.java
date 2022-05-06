package ru.otus.homework8;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.homework8.dao_repositories.BookRepository;
import ru.otus.homework8.models.Book;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class Homework8Application {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(Homework8Application.class);

        BookRepository bookRepository = ctx.getBean(BookRepository.class);

        bookRepository.save(new Book("KNIGA"));

        Thread.sleep(3000);

        System.out.println("Books:");
        bookRepository.findAll().forEach(book -> System.out.println(book.getTitle()));

        System.out.println("Find books by title");
        bookRepository.findBooksByTitle("Chaika").forEach(System.out::println);

    }

}
