package com.lupin.security.service;

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

import java.util.List;
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
    public Page<Books> getAllSortCountAsc(int start, int end) {
        Pageable sortedByName =
                PageRequest.of(start, end, Sort.by("count").ascending());
        Page<Books> page = bookRepository.findAll(sortedByName);
        return page;
    }

    @Override
    public Page<Books> getAllSortCountDsc(int start, int end) {
        Pageable sortedCount =
                PageRequest.of(start, end, Sort.by("count").descending());
        Page<Books> page = bookRepository.findAll(sortedCount);
        return page;
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
    public void addBook(Books book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(int id) {
        Optional<Books> book = bookRepository.findById(id);
        if (book.isPresent()) bookRepository.deleteById(id);
        else {
            System.out.println("This ID does not exit");
        }
    }

    @Override
    public void deleteBookByName(String name) {
        Optional<Books> book = bookRepository.findByName(name);
        if (book.isPresent()) bookRepository.deleteById(book.get().getId());
        else {
            System.out.println("This ID does not exit");
        }
    }

    @Override
    public void updateBookById(int id, int count) {
        Optional<Books> book = bookRepository.findById(id);
        if (book.isPresent()) {
            book.get().setCount(count);
            bookRepository.save(book.get());
        }
        else {

        }
    }

    @Override
    public void updateBookBynName(String name, int count) throws BookException{
        Optional<Books> book = bookRepository.findByName(name);
        if (book.isPresent()) {
            book.get().setCount(count);
            bookRepository.save(book.get());
        }
        else {
            throw new BookException("name's book is not exist");
        }
    }

    @Override
    public Books findById(int id) {
        return bookRepository.findById(id).get();
    }

}
