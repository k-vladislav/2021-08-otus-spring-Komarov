package ru.otus.Homework5.service;

import org.springframework.stereotype.Service;
import ru.otus.Homework5.dao.LinkLibraryDao;

@Service
public class LinkLibraryServiceImpl implements LinkLibraryService {

    private final LinkLibraryDao dao;

    public LinkLibraryServiceImpl(LinkLibraryDao dao) {
        this.dao = dao;
    }


    @Override
    public void linkBookAuthor(Long bookId, Long authorId) {
        if (dao.getIdByValueBookAuthor(bookId, authorId).isEmpty()) {
            dao.linkBookAuthor(bookId, authorId);
        }
    }

    @Override
    public void linkBookGenre(Long bookId, Long genreId) {
        if (dao.getIdByValueBookGenre(bookId, genreId).isEmpty()) {
            dao.linkBookGenre(bookId, genreId);
        }
    }
}
