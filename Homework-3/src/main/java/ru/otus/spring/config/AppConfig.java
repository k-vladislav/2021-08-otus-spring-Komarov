package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quiz")
public class AppConfig {
    private String path;
    private int requiredScore;
    private String localeLabel;

    @Bean
    public String getPath() {
        return path;
    }

    @Bean
    public int getRequiredScore() {
        return requiredScore;
    }

    @Bean
    public String getLocaleLabel() {
        return localeLabel;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setRequiredScore(int requiredScore) {
        this.requiredScore = requiredScore;
    }

    public void setLocaleLabel(String localeLabel) {
        this.localeLabel = localeLabel;
    }
}
