package ru.otus.homework7.service;


public interface AuthorService {

    boolean addAuthorForBook(String title, String lastName);

    boolean deleteAuthorFromBook(String title, String lastName);
}
