package ru.otus.homework6.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Author") //todo Как побороть ошибку Intellij Idea (Ultimate), что "Cannot resolve table/column X"? При этом Data Source выбран и указан (H2), а код (добавить-удалить книгу и т.д.) работает
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
    //todo В @ManyToMany отношениях, таблица связей @JoinTable указывается только у owning side (Book) или и у дочерней (Author, Genre) тоже?
    private Set<Book> books;


    @Override
    public String toString() {
        return lastName;
    }
}
