package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:HW2.properties")
public class AppConfig {
    private static int requiredScore;

    @Value("${quiz.required.score}")
    public void setRequiredAnswersCount(int requiredAnswersCount) {
        AppConfig.requiredScore = requiredAnswersCount;
    }

    public static int getRequiredScore() {
        return requiredScore;
    }

}
