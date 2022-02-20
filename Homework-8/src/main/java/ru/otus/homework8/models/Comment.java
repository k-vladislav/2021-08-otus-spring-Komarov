package ru.otus.homework8.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "BOOK_COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    private Book book;

    @Override
    public String toString() {
        return "id = " + id + ": " + comment;
    }
}