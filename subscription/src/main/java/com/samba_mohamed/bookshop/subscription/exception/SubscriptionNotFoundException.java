package com.samba_mohamed.bookshop.subscription.exception;

public class SubscriptionNotFoundException extends RuntimeException{
    public SubscriptionNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
