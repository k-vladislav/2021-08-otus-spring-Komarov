package ru.otus.homework6.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/*@Setter
@Getter
@NoArgsConstructor*/
@Entity
public class BookComment { //todo не может сущ-ть без Book
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false, unique = false)
    private String comment;

}
