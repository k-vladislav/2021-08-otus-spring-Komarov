package ru.otus.homework6.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
@NamedEntityGraph(name = "book-all-attributes-graph", includeAllAttributes = true)
@NamedEntityGraph(name = "book-with-comments", attributeNodes = @NamedAttributeNode("comments"))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    public Book(String title) {
        this.title = title;
    }

    /*
     todo
        Для связи Книги-Авторы используется @ManyToMany с таблицей связей.
        Главной сущностью выбрал Книгу (в нее добавляю автора или удаляю автора).
        Каскадные операции выбраны CascadeType.PERSIST, CascadeType.MERGE.
        Тип коллекций Авторов в Книге и Книг в Авторе - Set
        В создании таблиц в data.sql указал UNIQUE, чтобы Авторы были уникальные и Книги тоже
        Действия:
        1. Добавляю книгу КНИГА_1 - ОК
        2. Добавляю к книге КНИГА_1 автора АВТОР_1 - ОК
        3. Добавляю к книге КНИГА_1 автора АВТОР_2 - ОК
        4. Добавляю к книге КНИГА_1 автора АВТОР_1 - ожидаемая ошибка про "unique constrain violation", но хочется, чтобы либо ничего не делалось, либо просто обновилась таблица связей
        5. Добавляю книгу КНИГА_2 - ОК
        6. Добавляю к книге КНИГА_2 автора АВТОР_1 - ошибка "unique constrain violation", потому что гибернейт пытается вставить АВТОР_1 в таблицу Авторы, а такой Автор там уже есть.
            Как сделать, чтобы только добавилась связь КНИГА_2-АВТОР_1 в таблице связей, без добавления в таблицу уже такого автора?
     */

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "Book_Author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "Book_Genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public void addAuthor(Author author) {
        this.authors.add(author);
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
        genre.getBooks().add(this);
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getBooks().remove(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setBook(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setBook(null);
    }


    @Override
    public String toString() {
        return title;
    }
}
