package com.samba_mohamed.bookshop.book.serviceImpl;


import com.samba_mohamed.bookshop.book.Exception.BookNotFoundException;
import com.samba_mohamed.bookshop.book.model.Book;
import com.samba_mohamed.bookshop.book.repository.BookRepository;
import com.samba_mohamed.bookshop.book.serviceInterface.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Le livre avec l'id "+id+" est introuvale" ));
    }

    public List<Book> searchBook(String keyword) {
        List<Book> livres = bookRepository.findByTitreContainingIgnoreCase(keyword);
        livres.addAll(bookRepository.findByAuteurContainingIgnoreCase(keyword));
        return livres;
    }

    @Override
    public List<Book> createManyBooks(List<Book> livres) {
        return bookRepository.saveAll(livres);
    }

    public Book searchBookByTitre(String titre) {
        return bookRepository.findByTitreIgnoreCase(titre)
                .orElseThrow(() -> new BookNotFoundException("Le livre avec le titre '"+titre+"' est introuvale"));
    }

    public List<Book> getBooksByAuteur(String auteur){
        List<Book> livres = bookRepository.findByAuteurIgnoreCase(auteur);
        return livres;
    }

    public List<Book> getBooksByAuteurContainAuteurName(String auteur){
        List<Book> livres = bookRepository.findByAuteurContainingIgnoreCase(auteur);
        return livres;
    }

    public Book createBook(Book livre) {
        return bookRepository.save(livre);
    }

    public Book updateBook(Long id, Book livreDetails) {
        Book livre = getBookById(id);
        livre.setTitre(livreDetails.getTitre());
        livre.setAuteur(livreDetails.getAuteur());
        livre.setIsbn(livreDetails.getIsbn());
        livre.setDisponible(livreDetails.isDisponible());
        return bookRepository.save(livre);
    }

    public void deleteBook(Long id) {
        //gérer le fait que si lié à un emprunt il y aura probleme
        //peut etre gérer sa avec le field disponible. A voir

        bookRepository.delete(getBookById(id));
    }

    public List<Book> getLivresDisponibles() {
        return bookRepository.findByDisponibleTrue();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Le livre avec l'isbn "+isbn+" est introuvale"));
    }
}