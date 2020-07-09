package com.lupin.security.controller;

import com.lupin.security.model.Books;
import com.lupin.security.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookServiceImpl bookServiceimpl;

    @GetMapping("/getBooks")
    public List<Books> getAll() {
        return bookServiceimpl.getAll();
    }

    @GetMapping("/getBookSortedByCountAsc")
    public Page<Books> getSortCountAsc(@Param("start") int start, @Param("end") int end){
        return bookServiceimpl.getAllSortCountAsc(start, end);
    }

    @GetMapping("/getBookSortedByCountDesc")
    public Page<Books> getSortCountDesc(@Param("start") int start, @Param("end") int end){
        return bookServiceimpl.getAllSortCountDsc(start, end);
    }

    @GetMapping("/bookHasMaxCount")
    public Books maxCount(){
        return bookServiceimpl.maxCount();
    }

    @GetMapping("/bookHasMinCount")
    public Books minCount(){
        return bookServiceimpl.minCount();
    }

    @GetMapping("/getBookSortedByNameAsc")
    public Page<Books> getSortNameAsc(@Param("start") int start, @Param("end") int end){
        return bookServiceimpl.getAllSortNameAsc(start, end);
    }

    @GetMapping("/getBookSortedByNameDesc")
    public Page<Books> getSortNameDesc(@Param("start") int start, @Param("end") int end){
        return bookServiceimpl.getAllSortNameDsc(start, end);
    }

    @PostMapping("/addBook")
    public String add(@RequestBody Books book){
        bookServiceimpl.addBook(book);
        return "add success";
    }

    @DeleteMapping("/removeBookId")
    public String deleteBook(@Param("id") int id){
        bookServiceimpl.deleteBookById(id);
        return "delete complete id = " + id;
    }

    @DeleteMapping("/removeBookByName")
    public String deleteBookName(@Param("name") String name){
        bookServiceimpl.deleteBookByName(name);
        return "delete success book named : " + name;
    }

    @PutMapping("/updateBookByID")
    public String updateBookId(@Param("id") int id,@Param("count")int count){
        bookServiceimpl.updateBookById(id, count);
        return "update complete";
    }

    @PutMapping("updateBookByName")
    public String updateBookByName(@Param("name") String name, @Param("count") int count){
        bookServiceimpl.updateBookBynName(name, count);
        return "update success";
    }

}
