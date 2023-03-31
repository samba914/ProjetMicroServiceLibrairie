package com.samba_mohamed.bookshop.subscription_plan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SubscriptionPlanNotFoundException.class)
    protected ResponseEntity<Object> handleEmpruntNotFound(SubscriptionPlanNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(SubscriptionPlanAlreadyExistsException.class)
    protected ResponseEntity<Object> handlePlanAlreadyExists(SubscriptionPlanAlreadyExistsException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}