package ru.otus.Homework5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.Homework5.domain.Author;
import ru.otus.Homework5.domain.Book;
import ru.otus.Homework5.domain.Genre;
import ru.otus.Homework5.service.library.LibraryService;
import ru.otus.Homework5.service.link.LinkingService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MainLibraryServiceImpl implements MainLibraryService {

    private final LibraryService<Book> bookService;
    private LibraryService<Author> authorService;
    private LibraryService<Genre> genreService;
    private LinkingService<Author> linkAuthor;
    private LinkingService<Genre> linkGenre;

    public MainLibraryServiceImpl(LibraryService<Book> bookService) {
        this.bookService = bookService;
    }

    @Autowired(required = false)
    public void setAuthorService(LibraryService<Author> authorService) {
        this.authorService = authorService;
    }

    @Autowired(required = false)
    public void setGenreService(LibraryService<Genre> genreService) {
        this.genreService = genreService;
    }

    @Autowired(required = false)
    public void setLinkAuthor(LinkingService<Author> linkAuthor) {
        this.linkAuthor = linkAuthor;
    }

    @Autowired(required = false)
    public void setLinkGenre(LinkingService<Genre> linkGenre) {
        this.linkGenre = linkGenre;
    }


    @Override
    public long addBook(String title) {
        return bookService.add(title);
    }

    @Override
    public Optional<Book> showBook(String title) {

        return bookService.getId(title).map(bookId -> {

            List<Long> authorsId = linkAuthor.getListOfIdByBookId(bookId);
            List<Author> authors = authorService.getByListOfId(authorsId);

            List<Long> genresIds = linkGenre.getListOfIdByBookId(bookId);
            List<Genre> genres = genreService.getByListOfId(genresIds);

            Book book = new Book(title);
            book.setAuthors(authors);
            book.setGenres(genres);

            return book;
        });
    }

    @Override
    public int updateBookTitle(String oldTitle, String newTitle) {
        Optional<Long> bookIdOpt = bookService.getId(oldTitle);
        return bookIdOpt.map(aLong -> bookService.update(aLong, newTitle)).orElse(0);
    }

    @Override
    public int deleteBook(String title) {
        return bookService.getId(title).map(bookId -> {
            int res = bookService.delete(bookId);
            linkAuthor.deleteByBookId(bookId);
            linkGenre.deleteByBookId(bookId);
            return res;
        }).orElse(0);
    }

    @Override
    public long addAuthorForBook(String title, String lastName) {
        return bookService.getId(title).map(bookId -> {
            long authorId = authorService.add(lastName);
            return linkAuthor.link(bookId, authorId);
        }).orElse(0L);
    }

    @Override
    public List<Book> showBooksOfAuthor(String lastName) {
        return authorService.getId(lastName).map(authorId -> {
            List<Long> booksId = linkAuthor.getListOfBookIdById(authorId);
            return bookService.getByListOfId(booksId);
        }).orElse(Collections.emptyList());
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
        return authorService.getId(lastName)
                .map(authorId -> {
                    linkAuthor.deleteById(authorId);
                    return authorService.delete(authorId);
                }).orElse(0);
    }

    @Override
    public long addGenreForBook(String title, String genre) {
        return bookService.getId(title).map(bookId -> {
            long genreId = genreService.add(genre);
            return linkGenre.link(bookId, genreId);
        }).orElse(0L);

    }

    @Override
    public List<Book> showBooksOfGenre(String genre) {
        List<Long> booksId = genreService.getId(genre)
                .map(genreId -> linkGenre.getListOfBookIdById(genreId))
                .orElse(Collections.emptyList());

        return bookService.getByListOfId(booksId);
    }

    @Override
    public int updateGenre(String oldGenre, String newGenre) {
        return genreService.getId(oldGenre).map(id -> genreService.update(id, newGenre)).orElse(0);
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

        return genreService.getId(genre).map(genreId -> {
            linkGenre.deleteById(genreId);
            return genreService.delete(genreId);
        }).orElse(0);

    }
}
