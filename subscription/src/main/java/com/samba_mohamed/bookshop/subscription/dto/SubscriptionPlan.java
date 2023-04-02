package com.samba_mohamed.bookshop.subscription.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPlan {

    private Long id;

    private String nom;

    private Integer dureeEnMois;

    private Integer nombreEmpruntParSemaine;
}
