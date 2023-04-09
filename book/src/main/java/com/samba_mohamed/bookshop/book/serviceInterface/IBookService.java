package com.samba_mohamed.bookshop.book.serviceInterface;

import com.samba_mohamed.bookshop.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();

    List<Book> searchBook(String keyword);
    List<Book> createManyBooks(List<Book> livres);

    Book searchBookByTitre(String titre);

    List<Book> getBooksByAuteur(String auteur);

    List<Book> getBooksByAuteurContainAuteurName(String auteur);

    Book createBook(Book livre);

    Book updateBookState(String isbn, boolean isDisponible);

    Book updateBook(String isbn, Book livreDetails);

    void deleteBook(String isbn);

    List<Book> getLivresDisponibles();

    Book getBookByIsbn(String isbn);
}
