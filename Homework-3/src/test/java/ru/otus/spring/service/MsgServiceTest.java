package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {MsgServiceTest.class, MessageSourceAutoConfiguration.class})
@TestComponent
class MsgServiceTest {

    @Value(value = "RU")
    private String localeLabel;

    @Autowired
    private MessageSource msgSrc;

    @Test
    void out() {
        String message = "test.msgservice";
        String out = msgSrc.getMessage(message, null, Locale.forLanguageTag(localeLabel));
        if ("RU".equalsIgnoreCase(localeLabel)) {
            assertEquals("Тест Мсг Сервис", out);
        } else {
            assertEquals("Test Msg Service", out);
        }
    }
}