package ru.otus.Homework5.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.otus.Homework5.domain.Author;
import ru.otus.Homework5.domain.Book;
import ru.otus.Homework5.domain.Genre;
import ru.otus.Homework5.service.library.LibraryService;
import ru.otus.Homework5.service.link.LinkLibraryService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class WrapLibraryServiceImpl implements WrapLibraryService {

    private final LibraryService<Book> bookService;
    private LibraryService<Author> authorService;
    private LibraryService<Genre> genreService;
    private LinkLibraryService<Author> linkAuthor;
    private LinkLibraryService<Genre> linkGenre;

    public WrapLibraryServiceImpl(LibraryService<Book> bookService) {
        this.bookService = bookService;
    }

/*
    public void setLinkAuthor(LinkLibraryService<Author> linkAuthor) {
        this.linkAuthor = linkAuthor;
    }

    public void setLinkGenre(LinkLibraryService<Genre> linkGenre) {
        this.linkGenre = linkGenre;
    }

    private LinkLibraryService<Genre> linkGenre;

    @Autowired
    public void setAuthor(LibraryService<Author> author) {
        this.author = author;
    }

    @Autowired
    public void setGenre(LibraryService<Genre> genre) {
        this.genre = genre;
    }
*/


    @Override
    public long addBook(String title) {
       return bookService.add(title);
    }

    @Override
    public Optional<Book> showBook(String title) {
        Optional<Long> bookIdOpt = bookService.getId(title);
        if (bookIdOpt.isPresent()) {
            long bookId = bookIdOpt.get();
            List<Author> authors = linkAuthor.getListOfIdByBookId(bookId).stream()
                    .map(authorService::getById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            List<Genre> genres = linkGenre.getListOfIdByBookId(bookId).stream()
                    .map(genreService::getById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            Book book = new Book(title);
            book.setAuthors(authors);
            book.setGenres(genres);
            return Optional.of(book);
        }
        return Optional.empty();
    }

    @Override
    public int updateBook(String oldTitle, String newTitle) {
        Optional<Long> bookIdOpt = bookService.getId(oldTitle);
        return bookIdOpt.map(aLong -> bookService.update(aLong, newTitle)).orElse(0);
    }

    @Override
    public int deleteBook(String title) {
        //return book.getId(title).map(book::delete).orElse(0);
        int res = 0;
        Optional<Long> bookIdOpt = bookService.getId(title);
        if (bookIdOpt.isPresent()) {
            long bookId = bookIdOpt.get();
            res = bookService.delete(bookId);
            linkAuthor.deleteByBookId(bookId);
            linkGenre.deleteByBookId(bookId);
        }
        return res;
    }

    @Override
    public long addAuthorForBook(String title, String lastName) {
        long linkId = 0L;
        Optional<Long> bookIdOpt = bookService.getId(title);
        Optional<Long> authorIdOpt = authorService.getId(lastName);
        if (bookIdOpt.isPresent() && authorIdOpt.isPresent()) {
            linkId = linkAuthor.link(bookIdOpt.get(), authorIdOpt.get());
        }
        return linkId;
    }

    @Override
    public List<Book> showBooksOfAuthor(String lastName) {
        Optional<Long> authorIdOpt = authorService.getId(lastName);
        if (authorIdOpt.isPresent()) {
            Long authorId = authorIdOpt.get();
            List<Long> listOfBookId = linkAuthor.getListOfBookIdById(authorId);
            return listOfBookId.stream()
                    .map(bookService::getById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public int updateAuthor(String oldLastName, String newLastName) {
        return authorService.getId(oldLastName).map(id -> authorService.update(id, newLastName)).orElse(0);
    }

    @Override
    public long deleteAuthorForBook(String title, String lastName) {
        long linkId = 0L;
        Optional<Long> bookIdOpt = bookService.getId(title);
        Optional<Long> authorIdOpt = authorService.getId(lastName);
        if (bookIdOpt.isPresent() && authorIdOpt.isPresent()) {
            linkId = linkAuthor.delete(bookIdOpt.get(), authorIdOpt.get());
        }
        return linkId;
    }

    @Override
    public int deleteAuthorTotally(String lastName) {
        int delete = 0;
        Optional<Long> authorIdOpt = authorService.getId(lastName);
        if (authorIdOpt.isPresent()) {
            long authorId = authorIdOpt.get();
            delete = authorService.delete(authorId);
            linkAuthor.deleteById(authorId);
        }
        return delete;
    }

    @Override
    public long addGenreForBook(String title, String genre) {
        long linkId = 0L;
        Optional<Long> bookIdOpt = bookService.getId(title);
        Optional<Long> genreIdOpt = authorService.getId(genre);
        if (bookIdOpt.isPresent() && genreIdOpt.isPresent()) {
            linkId = linkGenre.link(bookIdOpt.get(), genreIdOpt.get());
        }
        return linkId;
    }

    @Override
    public List<Book> showBooksOfGenre(String genre) {
        Optional<Long> genreIdOpt = authorService.getId(genre);
        if (genreIdOpt.isPresent()) {
            Long genreId = genreIdOpt.get();
            List<Long> listOfBookId = linkGenre.getListOfBookIdById(genreId);
            return listOfBookId.stream()
                    .map(bookService::getById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public int updateGenre(String oldGenre, String newGenre) {
        return genreService.getId(oldGenre).map(id-> genreService.update(id,newGenre)).orElse(0);
    }

    @Override
    public long deleteGenreForBook(String title, String genre) {
        long linkId = 0L;
        Optional<Long> bookIdOpt = bookService.getId(title);
        Optional<Long> genreIdOpt = genreService.getId(genre);
        if (bookIdOpt.isPresent() && genreIdOpt.isPresent()) {
            linkId = linkGenre.delete(bookIdOpt.get(), genreIdOpt.get());
        }
        return linkId;
    }

    @Override
    public int deleteAGenreTotally(String genre) {
        int delete = 0;
        Optional<Long> genreIdOpt = genreService.getId(genre);
        if (genreIdOpt.isPresent()) {
            long genreId = genreIdOpt.get();
            delete = genreService.delete(genreId);
            linkGenre.deleteById(genreId);
        }
        return delete;
    }
}
