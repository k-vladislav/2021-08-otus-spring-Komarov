package ru.otus.homework6.service.implementations;

import org.springframework.stereotype.Service;
import ru.otus.homework6.dao_repositories.LibraryRepository;
import ru.otus.homework6.models.Book;
import ru.otus.homework6.service.BookService;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final LibraryRepository<Book> bookRep;

    public BookServiceImpl(LibraryRepository<Book> bookRep) {
        this.bookRep = bookRep;
    }

    @Override
    public boolean persistBook(String title) {
        Book book = new Book(title);
        bookRep.insert(book);
        return bookRep.contains(book); //todo testing
    }

    @Override
    public Optional<Book> showBook(String title) {
        return Optional.empty();
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
    public boolean deleteBook(String title) {
        //bookRep.findByValue(title).ifPresentOrElse(bookRep::delete, () -> System.out.println("Book not found: " + title));

        Optional<Boolean> isDeleted = bookRep.findByValue(title)
                .map(book -> {
                    bookRep.delete(book);
                    return !bookRep.contains(book);
                });

        return isDeleted.orElse(false);
    }
}
