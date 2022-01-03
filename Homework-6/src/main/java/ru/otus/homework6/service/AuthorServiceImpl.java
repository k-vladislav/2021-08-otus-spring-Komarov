package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.dao_repositories.LibraryRepository;
import ru.otus.homework6.models.Author;
import ru.otus.homework6.models.Book;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/* todo
    Реализованы методы addAuthorForBook и deleteAuthorForBook, которые действуют очень похоже:
    по названию книги загружается сущность Книга, в которой вызывается метод getAuthors, далее добавляю/удаляю автора из сета, делаю book.setAuthors и сохраняю (merge).
    Корректен ли такой подход, или нужно добавлять автора к книге по ID книги (т.е. нужен отдельный метод long getId(String title) в репозитории)
    Для жанров - так же как для авторов.
    Комментарии: отношение с книгами @OneToMany, но на уровне Service код операций по добавлению/удалению комментариев такой же как у авторов и жанров?
 */

@Service
public class AuthorServiceImpl implements AuthorService {

    private final LibraryRepository<Book> booksRepository;

    public AuthorServiceImpl(LibraryRepository<Book> booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    @Transactional
    public boolean addAuthorForBook(String title, String lastName) {
        Optional<Boolean> aBoolean = booksRepository.findByName(title).map(book -> {
            book.addAuthor(new Author(lastName));
/*            Set<Author> authors = book.getAuthors();
            authors.add(new Author(lastName));
            book.setAuthors(authors);*/
            booksRepository.save(book);
            return true;
        });
        return aBoolean.orElse(false);
    }

    @Override
    @Transactional
    public boolean deleteAuthorForBook(String title, String lastName) {
        Optional<Boolean> aBoolean = booksRepository.findByName(title).map(book -> {
            Set<Author> authors = book.getAuthors();
            Set<Author> cleanAuthors = authors.stream()
                    .filter(author -> !lastName.equalsIgnoreCase(author.getLastName()))
                    .collect(Collectors.toSet());
            book.setAuthors(cleanAuthors);
            booksRepository.save(book);
            return true;
        });
        return aBoolean.orElse(false);
    }
}
