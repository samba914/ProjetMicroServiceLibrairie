package com.samba_mohamed.bookshop.loan_management.exception;

public class BookNotAvailableException extends RuntimeException{
    public BookNotAvailableException(String errorMessage){
    super(errorMessage);}
}
