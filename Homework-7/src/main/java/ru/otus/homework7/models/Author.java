package ru.otus.homework7.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name", nullable = false, unique = true)
    private String lastName;

    public Author(String lastName) {
        this.lastName = lastName;
    }

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;


    @Override
    public String toString() {
        return lastName;
    }
}
