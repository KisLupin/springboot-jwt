package com.lupin.security.service;

import com.lupin.security.common.Common;
import com.lupin.security.common.ErrorCode;
import com.lupin.security.exception.BookException;
import com.lupin.security.inter.BookService;
import com.lupin.security.model.Books;
import com.lupin.security.repository.BookRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Books> getAll() {
        Iterable<Books> booksIterator = bookRepository.findAll();
        return IteratorUtils.toList(booksIterator.iterator());
    }

    @Override
    public Page<Books> getPages(int page, int size) {
        Pageable sortedByName =
                PageRequest.of(page, size);
        Page<Books> pages = bookRepository.findAll(sortedByName);
        return pages;
    }

    @Override
    public Page<Books> getAllSortCountAsc(int page, int size) {
        Pageable sortedByName =
                PageRequest.of(page, size, Sort.by("count").ascending());
        Page<Books> pages = bookRepository.findAll(sortedByName);
        return pages;
    }

    @Override
    public Page<Books> getAllSortCountDsc(int page, int size) {
        Pageable sortedCount =
                PageRequest.of(page, size, Sort.by("count").descending());
        Page<Books> pages = bookRepository.findAll(sortedCount);
        return pages;
    }

    @Override
    public Books maxCount() {
        Pageable sortedCount =
                PageRequest.of(0, 1, Sort.by("count").descending());
        List<Books> page = bookRepository.findAll(sortedCount).getContent();
        return page.get(0);
    }

    @Override
    public Books minCount() {
        Pageable sortedCount =
                PageRequest.of(0, 1, Sort.by("count").ascending());
        List<Books> page = bookRepository.findAll(sortedCount).getContent();
        return page.get(0);
    }

    @Override
    public Page<Books> getAllSortNameAsc(int start, int end) {
        Pageable sortedName =
                PageRequest.of(start, end, Sort.by("name").ascending());
        Page<Books> books = bookRepository.findAll(sortedName);
        return books;
    }

    @Override
    public Page<Books> getAllSortNameDsc(int start, int end) {
        Pageable sortedName =
                PageRequest.of(start, end, Sort.by("name").descending());
        Page<Books> books = bookRepository.findAll(sortedName);
        return books;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBook(Books book) {
        if (book.getName() == null) {
            throw new BookException(Common.NO_NAME_BOOK);
        }
        else if (!bookRepository.findByName(book.getName()).isEmpty()) {
            throw new BookException(ErrorCode.FAIL_SAME_NAME.nameError);
        }
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(int id) {
        Optional<Books> book = bookRepository.findById(id);
        if (book.isEmpty()) throw new BookException(Common.ID_NOT_EXIST);
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteBookByName(String name) {
        Optional<Books> book = bookRepository.findByName(name);
        if (book.isEmpty()) throw new BookException(Common.NAME_NOT_EXIST);
        bookRepository.deleteById(book.get().getId());
    }

    @Override
    public void updateBookById(int id, int count) {
        Optional<Books> book = bookRepository.findById(id);
        book.get().setCount(count);
        bookRepository.save(book.get());
    }

    @Override
    public void updateBookByName(String name, int count){
        Optional<Books> book = bookRepository.findByName(name);
        if (book.isEmpty())
            throw new BookException(Common.UPDATE_BY_NAME_FAIL);
        book.get().setCount(count);
        bookRepository.save(book.get());
    }

    @Override
    public Books findById(int id) {
        Optional<Books> books = bookRepository.findById(id);
        if (books.isEmpty())
            throw new BookException("id not exist");
        return books.get();
    }
}
