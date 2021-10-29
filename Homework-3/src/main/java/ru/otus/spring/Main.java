package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.MainService;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        MainService mainService = ctx.getBean(MainService.class);
        mainService.start();
    }
}

