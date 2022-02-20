package ru.otus.homework8.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    public Book(String title) {
        this.title = title;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "Book_Author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "Book_Genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments;

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
