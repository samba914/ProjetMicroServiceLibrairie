package com.samba_mohamed.bookshop.book.handler;

import com.samba_mohamed.bookshop.book.Exception.BookNotAvailableException;
import com.samba_mohamed.bookshop.book.Exception.BookNotFoundException;
import com.samba_mohamed.bookshop.book.Exception.FormatISBNIncorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    protected ResponseEntity<Object> handleBookNotFound(BookNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(FormatISBNIncorrectException.class)
    protected ResponseEntity<Object> handleFormatISBNIncorrectException(FormatISBNIncorrectException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(BookNotAvailableException.class)
    protected ResponseEntity<Object> handleBookNotAvailable(BookNotAvailableException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}