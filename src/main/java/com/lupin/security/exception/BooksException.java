package com.lupin.security.exception;

public class BooksException extends Error{
    private String msg;

    public BooksException(String msg) {
        this.msg = msg;
    }
}
