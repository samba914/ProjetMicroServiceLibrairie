package com.samba_mohamed.bookshop.loan_management.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String isbn;
    private String titre;
    private String auteur;
    private String editeur;
    private int edition;
    private boolean disponible;


}