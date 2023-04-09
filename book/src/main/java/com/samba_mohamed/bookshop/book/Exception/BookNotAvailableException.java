package com.samba_mohamed.bookshop.book.Exception;

public class BookNotAvailableException extends RuntimeException{
    public BookNotAvailableException(String errorMessage){
        super(errorMessage);
    }
}
