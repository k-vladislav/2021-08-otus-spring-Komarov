package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quiz")
public class AppConfig {
    private String path;
    private String localeLabel;
    private int requiredScore;
    private String logType;

    @Bean
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Bean
    public int getRequiredScore() {
        return requiredScore;
    }

    public void setRequiredScore(int requiredScore) {
        this.requiredScore = requiredScore;
    }

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

   /* @Bean
    public Messenger getMessenger(MessageSource source, @Qualifier("getLocaleLabel") String localeLabel, @Qualifier("getLogType") String logType) {
        if ("text".equals(logType)) {
            return new FileMsgService();
        } else {
            return new MsgService(source, localeLabel);
        }
    }*/

}
