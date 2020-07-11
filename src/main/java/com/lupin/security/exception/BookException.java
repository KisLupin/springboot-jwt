package com.lupin.security.exception;

import java.util.NoSuchElementException;

public class BookException extends NoSuchElementException {
    public BookException(String s) {
        super(s);
    }

    public BookException() {
    }
}
