package ru.otus.homework8.service;


public interface AuthorService {

    boolean addAuthorForBook(long bookId, String lastName);

    boolean deleteAuthorFromBook(long bookId, long authorId);
}
