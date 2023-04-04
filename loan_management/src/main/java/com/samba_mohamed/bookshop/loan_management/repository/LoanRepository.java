package com.samba_mohamed.bookshop.loan_management.repository;

import com.samba_mohamed.bookshop.loan_management.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByReaderId(Long readerId);
    List<Loan> findByReaderIdAndDateRetourIsNull(Long  readerId);
    List<Loan> findByIsbnAndDateRetourIsNull(String  isbn);
    List<Loan> findByDateRetourIsNull();
}
