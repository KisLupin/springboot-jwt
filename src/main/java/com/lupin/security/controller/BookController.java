package com.lupin.security.controller;

import com.lupin.security.model.Books;
import com.lupin.security.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookServiceImpl bookServiceimpl;

    @GetMapping("/getBooks")
    public RequestEntity<?> getBooks() {
        return new RequestEntity<>(bookServiceimpl.getAll(), HttpMethod.GET, null);
    }

    @GetMapping("/getBookSortedByCountAsc")
    public RequestEntity<?> getSortCountAsc(@Param("start") int start, @Param("end") int end){
        return new RequestEntity<>(bookServiceimpl.getAllSortCountAsc(start, end), HttpMethod.GET, null);
    }

    @GetMapping("/getBookSortedByCountDesc")
    public RequestEntity<?> getSortCountDesc(@Param("start") int start, @Param("end") int end){
        return new RequestEntity<>(bookServiceimpl.getAllSortCountDsc(start, end), HttpMethod.GET, null);
    }

    @GetMapping("/bookHasMaxCount")
    public RequestEntity<?> maxCount(){
        return new RequestEntity<>(bookServiceimpl.maxCount(), HttpMethod.GET, null);
    }

    @GetMapping("/bookHasMinCount")
    public RequestEntity<?> minCount(){
        return new RequestEntity<>(bookServiceimpl.minCount(),HttpMethod.GET,null);
    }

    @GetMapping("/getBookSortedByNameAsc")
    public RequestEntity<?> getSortNameAsc(@Param("start") int start, @Param("end") int end){
        return new RequestEntity<>(bookServiceimpl.getAllSortNameAsc(start, end),HttpMethod.GET,null);
    }

    @GetMapping("/getBookSortedByNameDesc")
    public RequestEntity<?> getSortNameDesc(@Param("start") int start, @Param("end") int end){
        return new RequestEntity<>(bookServiceimpl.getAllSortNameDsc(start, end),HttpMethod.GET,null);
    }

    @PostMapping("/addBook")
    public RequestEntity<?> add(@RequestBody Books book){
        bookServiceimpl.addBook(book);
        return new RequestEntity<>("add user success",HttpMethod.POST,null);
    }

    @DeleteMapping("/removeBookId")
    public RequestEntity<?> deleteBook(@Param("id") int id){
        bookServiceimpl.deleteBookById(id);
        return new RequestEntity<>("delete complete id = " + id, HttpMethod.DELETE,null);
    }

    @DeleteMapping("/removeBookByName")
    public RequestEntity<?> deleteBookName(@Param("name") String name){
        bookServiceimpl.deleteBookByName(name);
        return new RequestEntity<>("delete success book named : " + name, HttpMethod.DELETE,null);
    }

    @PutMapping("/updateBookByID")
    public RequestEntity<?> updateBookId(@Param("id") int id,@Param("count")int count) {
        bookServiceimpl.updateBookById(id, count);
        return new RequestEntity<>("update complete : "+id, HttpMethod.PUT,null);
    }

    @PutMapping("updateBookByName")
    public RequestEntity<?> updateBookByName(@Param("name") String name, @Param("count") int count){
        bookServiceimpl.updateBookBynName(name, count);
        return new RequestEntity<>("update success : "+name, HttpMethod.PUT,null);
    }

}
