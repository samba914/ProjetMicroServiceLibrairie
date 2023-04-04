package com.samba_mohamed.bookshop.loan_management.exception;

public class SubscriptionPlanNotFoundException extends RuntimeException{
    public SubscriptionPlanNotFoundException (String errorMessage) {
        super(errorMessage);
    }
}
