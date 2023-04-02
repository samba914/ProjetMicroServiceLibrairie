package com.samba_mohamed.bookshop.subscription.handler;


import com.samba_mohamed.bookshop.subscription.exception.ReaderNotFoundException;
import com.samba_mohamed.bookshop.subscription.exception.SubscriptionNotFoundException;
import com.samba_mohamed.bookshop.subscription.exception.SubscriptionPlanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReaderNotFoundException.class)
    protected ResponseEntity<Object> handleReaderNotFound(ReaderNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(SubscriptionPlanNotFoundException.class)
    protected ResponseEntity<Object> handlePlanNotFound(SubscriptionPlanNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(SubscriptionNotFoundException.class)
    protected ResponseEntity<Object> handleEmpruntNotFound(SubscriptionNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}