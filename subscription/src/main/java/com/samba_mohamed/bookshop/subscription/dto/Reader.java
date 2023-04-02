package com.samba_mohamed.bookshop.subscription.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reader {

    private Long id;

    private String genre;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private String adresse;

}