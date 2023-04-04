package com.samba_mohamed.bookshop.loan_management.serviceImpl;

import com.samba_mohamed.bookshop.loan_management.dto.Book;
import com.samba_mohamed.bookshop.loan_management.exception.BookNotFoundException;
import com.samba_mohamed.bookshop.loan_management.serviceInterface.IBookClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BookClientService implements IBookClientService {
    @Value("${microservices.book-service-url}")
    private String bookClientUrl ;

    @Override
    public Book getBookByIsbn(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject(bookClientUrl + "/books/isbn/" + isbn, Book.class);

        return book;
    }
}
