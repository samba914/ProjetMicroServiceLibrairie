package com.samba_mohamed.bookshop.book.repository;

import com.samba_mohamed.bookshop.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByDisponibleTrue();

    List<Book> findByTitreContainingIgnoreCase(String titre);

    List<Book> findByAuteurContainingIgnoreCase(String auteur);

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByTitreIgnoreCase(String titre);

    List<Book> findByAuteurIgnoreCase(String auteur);
}
