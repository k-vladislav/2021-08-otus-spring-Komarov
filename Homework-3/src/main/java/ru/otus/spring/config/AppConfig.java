package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quiz")
public class AppConfig {
    private String path;
    private Settings settings;
    private String localeLabel;

    @Bean
    public String getLocaleLabel() {
        return localeLabel;
    }

    public void setLocaleLabel(String localeLabel) {
        this.localeLabel = localeLabel;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Bean
    public String getPath() {
        return path;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }


    public static class Settings {
        private int requiredScore;

        @Bean
        public int getRequiredScore() {
            return requiredScore;
        }

        public void setRequiredScore(int requiredScore) {
            this.requiredScore = requiredScore;
        }
    }
}
