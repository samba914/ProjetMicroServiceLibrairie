package com.samba_mohamed.bookshop.book.serviceImpl;


import com.samba_mohamed.bookshop.book.Exception.BookNotAvailableException;
import com.samba_mohamed.bookshop.book.Exception.BookNotFoundException;
import com.samba_mohamed.bookshop.book.Exception.FormatISBNIncorrectException;
import com.samba_mohamed.bookshop.book.model.Book;
import com.samba_mohamed.bookshop.book.repository.BookRepository;
import com.samba_mohamed.bookshop.book.serviceInterface.IBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchBook(String keyword) {
        List<Book> livres = bookRepository.findByTitreContainingIgnoreCase(keyword);
        livres.addAll(bookRepository.findByAuteurContainingIgnoreCase(keyword));
        return livres;
    }

    @Override
    public List<Book> createManyBooks(List<Book> livres) {
        List<Book> newList = new ArrayList<>();
        for(Book b : livres){
            newList.add(createBook(b));
        }
        return  newList;
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
        String isbn= livre.getIsbn();
        if (isbn.matches("\\d+") && (isbn.length() == 10 || isbn.length() == 13)) {
            return bookRepository.save(livre);
        } else {
            throw new FormatISBNIncorrectException("L'isbn doit être composée uniquement de chiffres et avoir une longueur de 10 ou 13 caractères.");
        }

    }

    public Book updateBook(String isbn, Book livreDetails) {
        Book livre = getBookByIsbn(isbn);
        livre.setTitre(livreDetails.getTitre());
        livre.setAuteur(livreDetails.getAuteur());
        livre.setIsbn(livreDetails.getIsbn());
        livre.setDisponible(livreDetails.isDisponible());
        return bookRepository.save(livre);
    }

    @Transactional
    public Book updateBookState(String isbn, boolean isDisponible){
        Book book = bookRepository.findByIsbnForUpdate(isbn)
                .orElseThrow(() -> new BookNotFoundException("Le livre avec l'ibn '"+isbn+"' est introuvale"));
        if(isDisponible==false && !book.isDisponible()){
            throw new BookNotAvailableException("Le livre n'est déjà plus disponible");
        }
        book.setDisponible(isDisponible);
        return  book;

    }

    public void deleteBook(String isbn) {
        //gérer le fait que si lié à un emprunt il y aura probleme ??
        //peut etre gérer sa avec le field disponible. A voir ultérieurement ...

        bookRepository.delete(getBookByIsbn(isbn));
    }

    public List<Book> getLivresDisponibles() {
        return bookRepository.findByDisponibleTrue();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Le livre avec l'isbn "+isbn+" est introuvale"));
    }

    public void deleteAllBooks(){
        bookRepository.deleteAll();
    }

}