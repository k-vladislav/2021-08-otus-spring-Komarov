package ru.otus.homework8.service.printer;

import ru.otus.homework8.models.Book;

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
    }
}
