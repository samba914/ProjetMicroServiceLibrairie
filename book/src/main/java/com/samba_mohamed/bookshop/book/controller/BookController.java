package com.samba_mohamed.bookshop.book.controller;

import com.samba_mohamed.bookshop.book.model.Book;
import com.samba_mohamed.bookshop.book.serviceInterface.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
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

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book livreDetails) {
        return bookService.updateBook(id, livreDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Livre supprimé avec succès");
    }

    @GetMapping("/disponible")
    public List<Book> getLivresDisponibles() {
        return bookService.getLivresDisponibles();
    }

    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

}