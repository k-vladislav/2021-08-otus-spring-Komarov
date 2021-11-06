package ru.otus.Homework5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.Homework5.dao.AuthorDao;

@SpringBootApplication
public class Homework5Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Homework5Application.class, args);
		AuthorDao authorDao = ctx.getBean(AuthorDao.class);
		authorDao.getById(1);

	}

}
