package com.samba_mohamed.bookshop.loan_management.exception;

public class LoanNumberExceedException extends RuntimeException{
    public LoanNumberExceedException (String errorMessage){
        super(errorMessage) ;
    }
}
