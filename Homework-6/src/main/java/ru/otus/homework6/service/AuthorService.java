package ru.otus.homework6.service;


public interface AuthorService {

    boolean addAuthorForBook(String title, String lastName);

    boolean deleteAuthorForBook(String title, String lastName);
}
