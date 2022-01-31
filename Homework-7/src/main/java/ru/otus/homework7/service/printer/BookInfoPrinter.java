package ru.otus.homework7.service.printer;

import ru.otus.homework7.models.Book;
import ru.otus.homework7.models.Comment;

import java.util.List;

public class BookInfoPrinter {

    /**
     * print book and authors (and genres)
     *
     * @param book Book
     */
    public static void print(Book book) {
        System.out.println("----");
        System.out.println("Title:");
        System.out.println("    " + book);
        System.out.println("Authors:");
        book.getAuthors().forEach(author -> System.out.println("    " + author));
        System.out.println("Genres:");
        book.getGenres().forEach(genre -> System.out.println("    " + genre));
        System.out.println("Comments:");
        List<Comment> comments = book.getComments();
        comments.forEach(comment -> System.out.println("  " + comment));

    }
}
