package ru.otus.homework7.service;


public interface AuthorService {

    boolean addAuthorForBook(String title, String lastName);

    boolean deleteAuthorForBook(String title, String lastName);
}
