package ru.otus.Homework5.service.link;

import ru.otus.Homework5.dao.link.BookLinksDao;

import java.util.List;
import java.util.Optional;

public abstract class AbstractLinkLibraryService<T> implements LinkLibraryService<T> {

    private final BookLinksDao<T> dao;

    public AbstractLinkLibraryService(BookLinksDao<T> dao) {
        this.dao = dao;
    }


    @Override
    public long link(long bookId, long id) {
        Optional<Long> idOpt = dao.getId(bookId, id);
        if (idOpt.isEmpty()){
            return dao.link(bookId,id);
        } else {
            return idOpt.get();
        }
    }

    @Override
    public Optional<Long> getId(long bookId, long id) {
        return dao.getId(bookId, id);
    }


    @Override
    public List<Long> getListOfIdByBookId(long bookId) {
        return dao.getListOfIdByBookId(bookId);
    }


    @Override
    public List<Long> getListOfBookIdById(long id) {
        return dao.getListOfBookIdById(id);
    }


    @Override
    public int deleteByBookId(long bookId) {
        return dao.deleteByBookId(bookId);
    }


    @Override
    public int deleteById(long id) {
        return dao.deleteById(id);
    }


    @Override
    public int delete(long bookId, long id) {
        return dao.delete(bookId, id);
    }
}
