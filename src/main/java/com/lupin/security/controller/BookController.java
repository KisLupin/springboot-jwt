package com.lupin.security.controller;

import com.lupin.security.common.Common;
import com.lupin.security.common.ErrorCode;
import com.lupin.security.exception.BookException;
import com.lupin.security.inter.BookService;
import com.lupin.security.model.ApiResponse;
import com.lupin.security.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")

public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity<?> getBooks() {
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.getAll(),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> getPagesBook(@PathVariable("page") int page, @PathVariable("size") int size){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID,
                bookService.getPages(page, size),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/asc/{page}/{size}")
    public ResponseEntity<?> getSortCountAsc(@PathVariable("page") int page, @PathVariable("size") int size){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.getAllSortCountAsc(page,size),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/desc/{page}/{size}")
    public ResponseEntity<?> getSortCountDesc(@PathVariable("page") int page, @PathVariable("size") int size){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID,bookService.getAllSortCountDsc(page, size),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/max")
    public ResponseEntity<?> maxCount(){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID,bookService.maxCount(),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/min")
    public ResponseEntity<?> minCount(){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.minCount(),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/name-asc")
    public ResponseEntity<?> getSortNameAsc(@PathVariable("page") int start, @PathVariable("size") int end){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.getAllSortNameAsc(start, end),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/name-desc")
    public ResponseEntity<?> getSortNameDesc(@PathVariable("page") int start, @PathVariable("size") int end){
        ApiResponse apiResponse = new ApiResponse(ErrorCode.SUCCESS.ID, bookService.getAllSortNameDsc(start, end),null,Common.SUCCESS);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/add")
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

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") int id){
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

    @DeleteMapping("/remove/{name}")
    public ResponseEntity<?> deleteBookName(@PathVariable("name") String name){
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

    @PutMapping("/updateById/{id}/{count}")
    public ResponseEntity<?> updateBookId(@PathVariable("id") int id, @PathVariable("count")int count) {
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

    @PutMapping("/updateByName/{name}/{count}")
    public ResponseEntity<?> updateBookByName(@PathVariable("name") String name, @PathVariable("count") int count) {
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

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id) {
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
