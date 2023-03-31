package com.samba_mohamed.bookshop.subscription_plan.exception;

public class SubscriptionPlanAlreadyExistsException extends RuntimeException{
    public SubscriptionPlanAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
