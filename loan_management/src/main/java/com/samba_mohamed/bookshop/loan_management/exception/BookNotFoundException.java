package com.samba_mohamed.bookshop.loan_management.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
