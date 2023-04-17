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

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/recherche")
    public List<Book> searchBook(@RequestParam String keyword) {
        return bookService.searchBook(keyword);
    }

    @GetMapping("/titre/{titre}")
    public Book searchBookByTitre(@PathVariable String titre) {
        return bookService.searchBookByTitre(titre);
    }

    @GetMapping("/auteur/containAuteurName/{auteur}")
    public List<Book> getBooksByAuteurContainAuteurName(@PathVariable String auteur) {
        return bookService.getBooksByAuteurContainAuteurName(auteur);
    }

    @GetMapping("/auteur/{auteur}")
    public List<Book> getBooksByAuteur(@PathVariable String auteur) {
        return bookService.getBooksByAuteur(auteur);
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book livre) {
        Book createdLivre = bookService.createBook(livre);
        return new ResponseEntity<>(createdLivre, HttpStatus.CREATED);
    }


    @PostMapping("/createmanybooks")
    public ResponseEntity<List<Book>> createManyBooks(@RequestBody List<Book> livres) {
        List<Book> createdLivres = bookService.createManyBooks(livres);
        return new ResponseEntity<>(createdLivres, HttpStatus.CREATED);
    }


    @PutMapping("/isbn/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book livreDetails) {
        return bookService.updateBook(isbn, livreDetails);
    }

    @PutMapping("/isbn/{isbn}/updateState")
    public Book updateBookState(@PathVariable String isbn, @RequestParam boolean disponible) {
        return bookService.updateBookState(isbn, disponible);
    }

    @DeleteMapping("/isbn/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.ok("Livre supprimé avec succès");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllBooks() {
        bookService.deleteAllBooks();
        return ResponseEntity.ok("Tous les livres supprimés avec succès");
    }

    @GetMapping("/disponible")
    public List<Book> getLivresDisponibles() {
        return bookService.getLivresDisponibles();

    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

}
