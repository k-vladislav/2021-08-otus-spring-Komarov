package ru.otus.spring.service;

public interface Messenger {
    void out(String message);
    void out(String message, String[] args);
}
