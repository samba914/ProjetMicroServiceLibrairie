package com.samba_mohamed.bookshop.book.Exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
