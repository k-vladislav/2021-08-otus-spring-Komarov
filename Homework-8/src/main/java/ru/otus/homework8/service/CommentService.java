package ru.otus.homework8.service;

import ru.otus.homework8.models.Comment;

import java.util.List;

public interface CommentService {

    boolean addCommentForBook(long bookId, String comment);

    List<Comment> getCommentsForBook(long bookId);
}
