package com.samba_mohamed.bookshop.loan_management.serviceImpl;

import com.samba_mohamed.bookshop.loan_management.dto.Book;
import com.samba_mohamed.bookshop.loan_management.exception.BookNotFoundException;
import com.samba_mohamed.bookshop.loan_management.exception.LoanException;
import com.samba_mohamed.bookshop.loan_management.serviceInterface.IBookClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

@Service
public class BookClientService implements IBookClientService {
    @Value("${microservices.book-service-url}")
    private String bookClientUrl ;

    @Override
    public Book getBookByIsbn(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Book> response = restTemplate.getForEntity(bookClientUrl + "/books/isbn/" + isbn, Book.class);
        return response.getBody();
    }

    @Override
    public void setBookState(String isbn, boolean disponible) {
        String url = bookClientUrl+"/books/isbn/{isbn}/updateState?disponible={disponible}";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Book> response = restTemplate.exchange(url, HttpMethod.PUT, null, Book.class, isbn, disponible);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new LoanException("Une erreur est survenue lors de la modification du status du livre!");
        }
    }
}
