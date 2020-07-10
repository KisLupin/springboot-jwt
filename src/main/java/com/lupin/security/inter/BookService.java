package com.lupin.security.inter;

import com.lupin.security.exception.BookException;
import com.lupin.security.model.Books;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<Books> getAll();
    Page<Books> getAllSortCountAsc(int start, int end);
    Page<Books> getAllSortCountDsc(int start, int end);
    Books maxCount();
    Books minCount();
    Page<Books> getAllSortNameAsc(int start, int end);
    Page<Books> getAllSortNameDsc(int start, int end);
    void addBook(Books book);
    void deleteBookById(int id);
    void deleteBookByName(String id);
    void updateBookById(int id, int count);
    void updateBookBynName(String name, int count) throws BookException;
    Books findById(int id);
}
