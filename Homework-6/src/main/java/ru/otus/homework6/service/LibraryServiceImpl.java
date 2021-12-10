package ru.otus.homework6.service;

import org.springframework.stereotype.Service;
import ru.otus.homework6.dao_repositories.LibraryRepository;
import ru.otus.homework6.models.Author;
import ru.otus.homework6.models.Book;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository<Book> bookRep;

    public LibraryServiceImpl(LibraryRepository<Book> bookRep) {
        this.bookRep = bookRep;
    }


    @Override
    public boolean persistBook(String title) {
        bookRep.insert(new Book(title));
        return true; //todo wtf?
    }

    @Override
    public void updateBookTitle(String oldTitle, String newTitle) {
        Optional<Book> bookOpt = bookRep.findByValue(oldTitle);
        bookOpt.ifPresentOrElse(book -> {
            book.setTitle(newTitle);
            Book mergedBook = bookRep.mergeUpdate(book);
            System.out.println("Book: title updated from " + book.getTitle() + " to " + mergedBook.getTitle());
        }, () -> System.out.println("Book not found: " + oldTitle));
    }

    @Override
    public Optional<Book> showBook(String title) {
        Optional<Book> bookOpt = bookRep.findByValue(title);
        bookOpt.ifPresentOrElse(book -> {
                    System.out.println(book);
                    System.out.println("Authors: ");
                    book.getAuthors().forEach(System.out::println);
                    System.out.println("Genres: ");
                    book.getGenres().forEach(System.out::println);
                },
                () -> System.out.println("Book not found: " + title)); //todo entity graph?
        return Optional.empty(); //todo return?
    }

    @Override
    public boolean deleteBook(String title) {
        bookRep.findByValue(title).ifPresentOrElse(bookRep::delete, () -> System.out.println("Book not found: " + title));
        return true; //todo wtf?
    }

    @Override
    public long addAuthorForBook(String title, String lastName) { //todo return?
        Optional<Book> bookOpt = bookRep.findByValue(title);
        bookOpt.ifPresentOrElse(book -> {
                    List<Author> authors = book.getAuthors();
                    authors.add(new Author(lastName));
                    book.setAuthors(authors);
                    bookRep.mergeUpdate(book);
                    System.out.println("Book " + title + ": author added: " + lastName);
                },
                () -> System.out.println("Book not found: " + title));
        return 0;
    }

    @Override
    public List<Book> showBooksOfAuthor(String lastname) {
        return null; //todo AuthorRep
    }

    @Override
    public int updateAuthorLastName(String oldLastName, String newLastName) {
        return 0; //todo AuthorRep
    }

    @Override
    public long deleteAuthorForBook(String title, String lastName) { //todo?
        Optional<Book> bookOpt = bookRep.findByValue(title);
        bookOpt.ifPresentOrElse(book -> {
                    List<Author> authors = book.getAuthors();
                    authors.stream().filter(author -> lastName.equalsIgnoreCase(author.getLastName())).forEach(authors::remove);
                    book.setAuthors(authors);
                    bookRep.mergeUpdate(book);
                    System.out.println("Book " + title + ": author added: " + lastName);
                },
                () -> System.out.println("Book not found: " + title));
        return 0;
    }

    @Override
    public int deleteAuthorTotally(String lastName) {
        return 0; //todo authorrep
    }

    @Override
    public long addGenreForBook(String title, String genre) {
        return 0;
    }

    @Override
    public List<Book> showBooksOfGenre(String genre) {
        return null;
    }

    @Override
    public int updateGenre(String oldGenre, String newGenre) {
        return 0;
    }

    @Override
    public long deleteGenreForBook(String title, String genre) {
        return 0;
    }

    @Override
    public int deleteAGenreTotally(String genre) {
        return 0;
    }
}
