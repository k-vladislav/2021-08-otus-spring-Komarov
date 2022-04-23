package ru.otus.homework8.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document
@Getter
@Setter
public class Book {

    @Id
    private String id;

    private String title;
    private Set<Author> authors;
    private Set<Genre> genres;
    private List<Comment> comments;

    public Book(String title) {
        this.title = title;
    }

    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = Stream.of(author).collect(Collectors.toSet());
        } else {
            this.authors.add(author);
        }
        if (author.getBooks() == null) {
            author.setBooks(Set.of(this));
        } else {
            author.getBooks().add(this);
        }
    }

    public void removeAuthor(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
        if (genre.getBooks() == null) {
            genre.setBooks(Set.of(this));
        } else {
            genre.getBooks().add(this);
        }
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getBooks().remove(this);
    }

    public void addComment(Comment comment) {
        if (this.comments == null) {
            this.comments = Stream.of(comment).collect(Collectors.toList());
        } else {
            this.comments.add(comment);
        }
        comment.setBook(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setBook(null);
    }


    @Override
    public String toString() {
        return "id = " + id + ": " + title;
    }
}
