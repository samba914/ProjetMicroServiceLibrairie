package com.samba_mohamed.bookshop.book.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {

    @Id
    private String isbn;
    private String titre;
    private String auteur;
    private String editeur;
    private int edition;
    private boolean disponible;


}