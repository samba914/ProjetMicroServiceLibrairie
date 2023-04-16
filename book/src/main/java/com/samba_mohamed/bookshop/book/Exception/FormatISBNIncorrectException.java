package com.samba_mohamed.bookshop.book.Exception;

public class FormatISBNIncorrectException extends RuntimeException{
    public FormatISBNIncorrectException(String errorMessage){
        super(errorMessage);
    }
}
