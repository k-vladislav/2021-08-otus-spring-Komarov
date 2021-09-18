package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.dao.QuizDao;
import ru.otus.spring.dao.QuizDaoCSV;
import ru.otus.spring.service.QuizService;
import ru.otus.spring.service.QuizServiceCSV;

@Configuration
@PropertySource(value = "classpath:HW2.properties")
public class AppConfig {

    private static int requiredScore;

    @Value("${quiz.required.score}")
    public void setRequiredAnswersCount(String requiredAnswersCount) {
        AppConfig.requiredScore = Integer.parseInt(requiredAnswersCount);
    }

    public static int getRequiredScore() {
        return requiredScore;
    }

    @Bean
    public QuizDao quizDao(@Value("${quiz.path}") String csvFilePath) {
        return new QuizDaoCSV(csvFilePath);
    }

    @Bean
    public QuizService quizService(QuizDao dao) {
        return new QuizServiceCSV(dao);
    }

}
