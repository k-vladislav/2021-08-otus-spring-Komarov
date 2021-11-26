package ru.otus.homework6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.homework6.models.Book;
import ru.otus.homework6.repositories.BookRepository;

@SpringBootApplication
public class Homework6Application {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(Homework6Application.class, args);

        BookRepository bean = run.getBean(BookRepository.class);
        bean.save(new Book("HOLOPOK"));
        bean.findById(1L).ifPresentOrElse(System.out::println,() -> System.out.println("Not found"));
        bean.findById(2L).ifPresentOrElse(System.out::println,() -> System.out.println("Not found"));

    }

}
