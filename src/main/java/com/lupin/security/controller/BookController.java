package com.lupin.security.controller;

import com.lupin.security.model.Books;
import com.lupin.security.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getBooks")
    public List<Books> getAll(){
        return bookService.getAll();
    }
}
