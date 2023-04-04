package com.samba_mohamed.bookshop.loan_management.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    private Long id;

    private Long readerId;

    private Long subscriptionPlanId;

    private LocalDate startDate;

    private LocalDate endDate;


}
