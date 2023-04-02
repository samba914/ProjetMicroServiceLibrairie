package com.samba_mohamed.bookshop.subscription.exception;

public class ReaderNotFoundException extends RuntimeException{
    public ReaderNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
