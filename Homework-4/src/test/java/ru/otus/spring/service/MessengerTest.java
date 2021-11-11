package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.MessageSource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
},classes = {MessengerTest.class, MessageSourceAutoConfiguration.class})
@TestComponent
class MessengerTest {

    @Value(value = "RU")
    private String localeLabel;

    @Autowired
    private MessageSource msgSrc;

    @Test
    @DisplayName("should print RU/EN messages depending on locale label")
    void output_Depending_On_LocaleLabel() {
        String message = "test.msgservice";
        String out = msgSrc.getMessage(message, null, Locale.forLanguageTag(localeLabel));
        if ("RU".equalsIgnoreCase(localeLabel)) {
            assertEquals("Тест Мсг Сервис", out);
        } else {
            assertEquals("Test Msg Service", out);
        }
    }
}