package com.samba_mohamed.bookshop.reader.exception;

public class ReaderNotFoundException extends RuntimeException{
    public ReaderNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
