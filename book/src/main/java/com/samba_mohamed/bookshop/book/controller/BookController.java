package com.samba_mohamed.bookshop.book.controller;

import com.samba_mohamed.bookshop.book.model.Book;
import com.samba_mohamed.bookshop.book.serviceInterface.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Cacheable(value = "booksCache")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Cacheable(value = "booksCache", key = "#keyword")
    @GetMapping("/recherche")
    public List<Book> searchBook(@RequestParam String keyword) {
        return bookService.searchBook(keyword);
    }

    @Cacheable(value = "booksCache", key = "#titre")
    @GetMapping("/titre/{titre}")
    public Book searchBookByTitre(@PathVariable String titre) {
        return bookService.searchBookByTitre(titre);
    }

    @Cacheable(value = "booksCache", key = "#auteur")
    @GetMapping("/auteur/containAuteurName/{auteur}")
    public List<Book> getBooksByAuteurContainAuteurName(@PathVariable String auteur) {
        return bookService.getBooksByAuteurContainAuteurName(auteur);
    }

    @Cacheable(value = "booksCache", key = "#auteur")
    @GetMapping("/auteur/{auteur}")
    public List<Book> getBooksByAuteur(@PathVariable String auteur) {
        return bookService.getBooksByAuteur(auteur);
    }

    @CachePut(value = "booksCache", key = "#livre.isbn")
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book livre) {
        Book createdLivre = bookService.createBook(livre);
        return new ResponseEntity<>(createdLivre, HttpStatus.CREATED);
    }

    @CachePut(value = "booksCache", key = "#livres.isbn")
    @PostMapping("/createmanybooks")
    public ResponseEntity<List<Book>> createManyBooks(@RequestBody List<Book> livres) {
        List<Book> createdLivres = bookService.createManyBooks(livres);
        return new ResponseEntity<>(createdLivres, HttpStatus.CREATED);
    }

    @CachePut(value = "booksCache", key = "#isbn")
    @PutMapping("/isbn/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book livreDetails) {
        return bookService.updateBook(isbn, livreDetails);
    }

    @CachePut(value = "booksCache", key = "#isbn")
    @PutMapping("/isbn/{isbn}/updateState")
    public Book updateBookState(@PathVariable String isbn, @RequestParam boolean disponible) {
        return bookService.updateBookState(isbn, disponible);
    }

    @CacheEvict(value = "booksCache", key = "#isbn")
    @DeleteMapping("/isbn/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.ok("Livre supprimé avec succès");
    }

    @Cacheable(value = "booksCache" ) /// a voir
    @GetMapping("/disponible")
    public List<Book> getLivresDisponibles() {
        return bookService.getLivresDisponibles();

    }

    @Cacheable(value = "booksCache" , key="#isbn")
    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

}