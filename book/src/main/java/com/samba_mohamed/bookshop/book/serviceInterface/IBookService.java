package com.samba_mohamed.bookshop.book.serviceInterface;

import com.samba_mohamed.bookshop.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    List<Book> searchBook(String keyword);
    List<Book> createManyBooks(List<Book> livres);

    Book searchBookByTitre(String titre);

    List<Book> getBooksByAuteur(String auteur);

    List<Book> getBooksByAuteurContainAuteurName(String auteur);

    Book createBook(Book livre);



    Book updateBook(Long id, Book livreDetails);

    void deleteBook(Long id);

    List<Book> getLivresDisponibles();

    Book getBookByIsbn(String isbn);
}
