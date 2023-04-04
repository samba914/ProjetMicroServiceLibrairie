package com.samba_mohamed.bookshop.loan_management.handler;



import com.samba_mohamed.bookshop.loan_management.exception.*;
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
    @ExceptionHandler(NoSubscriptionValidFoundForReaderException.class)
    protected ResponseEntity<Object> handleSubscriptionValidNotFound(NoSubscriptionValidFoundForReaderException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(BookNotFoundException.class)
    protected ResponseEntity<Object> handleBookNotFound(BookNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(SubscriptionPlanNotFoundException.class)
    protected ResponseEntity<Object> handleSubscriptionPlanNotFound(SubscriptionPlanNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(LoanNumberExceedException.class)
    protected ResponseEntity<Object> handleLoanNumberExceed(LoanNumberExceedException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(LoanException.class)
    protected ResponseEntity<Object> handleLoanException(LoanException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}