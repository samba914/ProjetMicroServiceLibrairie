package com.samba_mohamed.bookshop.loan_management.exception;

public class ReaderNotFoundException extends RuntimeException{
    public ReaderNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
