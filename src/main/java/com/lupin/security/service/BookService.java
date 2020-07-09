package com.lupin.security.service;

import com.lupin.security.model.Books;
import com.lupin.security.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Books> getAll() {
        return (List<Books>) bookRepository.findAll();
    }
}
