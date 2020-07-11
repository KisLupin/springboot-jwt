package com.lupin.security.controller;

import com.lupin.security.common.Common;
import com.lupin.security.common.ErrorCode;
import com.lupin.security.exception.BookException;
import com.lupin.security.inter.BookService;
import com.lupin.security.model.ApiResponse;
import com.lupin.security.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.awt.print.Book;

@RestController
@RequestMapping("/book")
public class BookController extends ResponseEntityExceptionHandler {
    @Autowired
    private BookService bookService;

    @GetMapping("/getBooks")
    public ResponseEntity<?> getBooks() {
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.getAll(),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getBookSortedByCountAsc")
    public ResponseEntity<?> getSortCountAsc(@Param("start") int start, @Param("end") int end){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.getAllSortCountAsc(start,end),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getBookSortedByCountDesc")
    public ResponseEntity<?> getSortCountDesc(@Param("start") int start, @Param("end") int end){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID,bookService.getAllSortCountDsc(start, end),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/bookHasMaxCount")
    public ResponseEntity<?> maxCount(){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID,bookService.maxCount(),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/bookHasMinCount")
    public ResponseEntity<?> minCount(){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.minCount(),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/getBookSortedByNameAsc")
    public ResponseEntity<?> getSortNameAsc(@Param("start") int start, @Param("end") int end){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.getAllSortNameAsc(start, end),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getBookSortedByNameDesc")
    public ResponseEntity<?> getSortNameDesc(@Param("start") int start, @Param("end") int end){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.getAllSortNameDsc(start, end),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> add(@RequestBody Books book){
        ApiResponse apiResponse;
        try {
            bookService.addBook(book);
            apiResponse= new ApiResponse(ErrorCode.SUCCESS.ID, null,null,Common.ADD_BOOK_SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (BookException e){
            apiResponse = new ApiResponse(ErrorCode.FAIL.ID, null, e.getMessage(), Common.ADD_BOOK_FAIL);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeBookId")
    public ResponseEntity<?> deleteBook(@Param("id") int id){
        ApiResponse apiResponse;
        try {
            bookService.deleteBookById(id);
            apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, null,null,Common.DELETE_SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (BookException e){
            apiResponse = new ApiResponse(ErrorCode.FAIL.ID, null, e.getMessage(), Common.DELETE_FAIL);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeBookByName")
    public ResponseEntity<?> deleteBookName(@Param("name") String name){
        ApiResponse apiResponse;
        try {
            bookService.deleteBookByName(name);
            apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, null,null,Common.DELETE_SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (BookException e){
            apiResponse = new ApiResponse(ErrorCode.FAIL.ID, null, e.getMessage(), Common.DELETE_FAIL);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateBookById")
    public ResponseEntity<?> updateBookId(@Param("id") int id,@Param("count")int count) {
        ApiResponse apiResponse;
        try {
            bookService.updateBookById(id, count);
            apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID,null,null,Common.UPDATE_SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (BookException exception){
            exception.printStackTrace();
            apiResponse = new ApiResponse(ErrorCode.FAIL.ID,null, exception.getMessage(), Common.UPDATE_FAIl);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            apiResponse = new ApiResponse(ErrorCode.FAIL.ID, e.getCause(), e.getMessage(), Common.UPDATE_FAIl);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateBookByName")
    public ResponseEntity<?> updateBookByName(@Param("name") String name, @Param("count") int count) {
        ApiResponse apiResponse;
        try {
            bookService.updateBookByName(name, count);
            apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID,null,null,Common.UPDATE_SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (BookException exception){
            exception.printStackTrace();
            apiResponse = new ApiResponse(ErrorCode.FAIL.ID,null, exception.getMessage(), Common.UPDATE_FAIl);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            apiResponse = new ApiResponse(ErrorCode.FAIL.ID, e.getCause(), e.getMessage(), Common.UPDATE_FAIl);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@Param("id") int id) {
        ApiResponse apiResponse;
        try {
            apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.findById(id),null, Common.FIND_SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (BookException bookException){
            apiResponse = new ApiResponse(ErrorCode.FAIL.ID, Common.ID_NOT_EXIST,ErrorCode.FAIL_SEARCH.name(), Common.FIND_FAIl);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }catch (Exception exception){
            apiResponse = new ApiResponse(ErrorCode.FAIL.ID, exception.getLocalizedMessage(),exception.getMessage(), Common.FIND_FAIl);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
