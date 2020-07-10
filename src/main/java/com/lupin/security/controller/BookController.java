package com.lupin.security.controller;

import com.lupin.security.exception.BookException;
import com.lupin.security.inter.BookService;
import com.lupin.security.inter.UserTokenService;
import com.lupin.security.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getBooks")
    public RequestEntity<?> getBooks() {
        return new RequestEntity<>(bookService.getAll(), HttpMethod.GET, null);
    }

    @GetMapping("/getBookSortedByCountAsc")
    public RequestEntity<?> getSortCountAsc(@Param("start") int start, @Param("end") int end){
        return new RequestEntity<>(bookService.getAllSortCountAsc(start, end), HttpMethod.GET, null);
    }

    @GetMapping("/getBookSortedByCountDesc")
    public RequestEntity<?> getSortCountDesc(@Param("start") int start, @Param("end") int end){
        return new RequestEntity<>(bookService.getAllSortCountDsc(start, end), HttpMethod.GET, null);
    }

    @GetMapping("/bookHasMaxCount")
    public RequestEntity<?> maxCount(){
        return new RequestEntity<>(bookService.maxCount(), HttpMethod.GET, null);
    }

    @GetMapping("/bookHasMinCount")
    public RequestEntity<?> minCount(){
        return new RequestEntity<>(bookService.minCount(),HttpMethod.GET,null);
    }

    @GetMapping("/getBookSortedByNameAsc")
    public RequestEntity<?> getSortNameAsc(@Param("start") int start, @Param("end") int end){
        return new RequestEntity<>(bookService.getAllSortNameAsc(start, end),HttpMethod.GET,null);
    }

    @GetMapping("/getBookSortedByNameDesc")
    public RequestEntity<?> getSortNameDesc(@Param("start") int start, @Param("end") int end){
        return new RequestEntity<>(bookService.getAllSortNameDsc(start, end),HttpMethod.GET,null);
    }

    @PostMapping("/addBook")
    public RequestEntity<?> add(@RequestBody Books book){
        bookService.addBook(book);
        return new RequestEntity<>("add user success",HttpMethod.POST,null);
    }

    @DeleteMapping("/removeBookId")
    public RequestEntity<?> deleteBook(@Param("id") int id){
        bookService.deleteBookById(id);
        return new RequestEntity<>("delete complete id = " + id, HttpMethod.DELETE,null);
    }

    @DeleteMapping("/removeBookByName")
    public RequestEntity<?> deleteBookName(@Param("name") String name){
        bookService.deleteBookByName(name);
        return new RequestEntity<>("delete success book named : " + name, HttpMethod.DELETE,null);
    }

    @PutMapping("/updateBookByID")
    public RequestEntity<?> updateBookId(@Param("id") int id,@Param("count")int count) {
        bookService.updateBookById(id, count);
        return new RequestEntity<>("update complete : "+id, HttpMethod.PUT,null);
    }

    @PutMapping("updateBookByName")
    public RequestEntity<?> updateBookByName(@Param("name") String name, @Param("count") int count) throws BookException{
        bookService.updateBookBynName(name, count);
        return new RequestEntity<>("update success : "+name, HttpMethod.PUT,null);
    }

    @GetMapping("/findById")
    public RequestEntity<?> findById(@Param("id") int id) throws BookException {
        try {
            return new RequestEntity<>(bookService.findById(id), HttpMethod.GET, null);
        }catch (Exception ex){
            throw new BookException("id not exits");
        }
    }
}
