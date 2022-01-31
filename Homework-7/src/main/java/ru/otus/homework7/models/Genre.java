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
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre", nullable = false, unique = true)
    private String genre;

    public Genre(String genre) {
        this.genre = genre;
    }

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;

    @Override
    public String toString() {
        return "id = " + id + ": " + genre;
    }
}
