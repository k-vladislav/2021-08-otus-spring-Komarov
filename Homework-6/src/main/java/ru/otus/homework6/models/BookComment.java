package ru.otus.homework6.models;

import javax.persistence.*;

@Entity
public class BookComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false, unique = false)
    private String comment;

}
