package com.samba_mohamed.bookshop.loan_management.exception;

public class LoanException extends RuntimeException{
    public LoanException (String errorMessage){
        super(errorMessage) ;
    }
}
