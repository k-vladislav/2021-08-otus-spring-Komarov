package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quiz")
public class AppConfig {
    private String path;
    private Settings settings;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Bean
    public String getPath() {
        return path;
    }


   public static class Settings {
        private int requiredscore;

        @Bean
        public int getRequiredscore() {
            return requiredscore;
        }

        public void setRequiredscore(int requiredscore) {
            this.requiredscore = requiredscore;
        }
    }
}
