package com.samba_mohamed.bookshop.loan_management.repository;

import com.samba_mohamed.bookshop.loan_management.model.Loan;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByReaderId(Long readerId);
    List<Loan> findByReaderIdAndDateRetourIsNull(Long readerId);

    List<Loan> findByIsbnAndDateRetourIsNull(String  isbn);

    List<Loan> findByDatePret(LocalDate date);
    List<Loan> findByDateRetourIsNull();
    Long countByReaderIdAndDatePretBetween(Long readerId, LocalDate start, LocalDate end) ;

    Optional<Loan> findByReaderIdAndIsbnAndDateRetourIsNull(Long readerId, String isbn);
}
