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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MainLibraryServiceImpl implements AuthorCommands, BookCommands, GenreCommands {

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

            List<Author> authors = linkAuthor.getListOfIdByBookId(bookId)
                    .stream().map(authorService::getById)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());

            List<Genre> genres = linkGenre.getListOfIdByBookId(bookId)
                    .stream().map(genreService::getById)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());

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
            return booksId.stream()
                    .map(bookService::getById)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
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
        if (bookIdOpt.isPresent()) {
            long genreId = genreService.add(genre);
            linkId = linkGenre.link(bookIdOpt.get(), genreId);
        }
        return linkId;
    }

    @Override
    public List<Book> showBooksOfGenre(String genre) {
        List<Long> booksId = genreService.getId(genre)
                .map(genreId -> linkGenre.getListOfBookIdById(genreId))
                .orElse(Collections.emptyList());

        return booksId.stream()
                .map(bookService::getById)
                .map(Optional::get)
                .collect(Collectors.toList());
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
