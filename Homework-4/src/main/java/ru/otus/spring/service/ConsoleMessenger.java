package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ConsoleMessenger implements Messenger {

    private final MessageSource msgSrc;
    private final String localeLabel;

    public ConsoleMessenger(MessageSource msgSrc, @Qualifier("getLocaleLabel") String localeLabel) {
        this.msgSrc = msgSrc;
        this.localeLabel = localeLabel;
    }

    @Override
    public void out(String message) {
        String out = msgSrc.getMessage(message, null, Locale.forLanguageTag(localeLabel));
        System.out.println(out);
    }

    @Override
    public void out(String message, String[] args) {
        String out = msgSrc.getMessage(message, args, Locale.forLanguageTag(localeLabel));
        System.out.println(out);
    }
}
