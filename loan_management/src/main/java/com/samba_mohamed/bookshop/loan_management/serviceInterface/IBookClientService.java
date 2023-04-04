package com.samba_mohamed.bookshop.loan_management.serviceInterface;


import com.samba_mohamed.bookshop.loan_management.dto.Book;

public interface IBookClientService {
    public Book getBookByIsbn(String isbn) ;
}
