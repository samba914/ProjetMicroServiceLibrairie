package com.samba_mohamed.bookshop.loan_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "reader_id")
    private Long readerId;

    @Column(name = "date_pret")
    private LocalDate datePret;

    @Column(name = "date_retour")
    private LocalDate dateRetour;
}
