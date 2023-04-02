package com.samba_mohamed.bookshop.subscription.exception;

public class SubscriptionPlanNotFoundException extends RuntimeException{
    public SubscriptionPlanNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
