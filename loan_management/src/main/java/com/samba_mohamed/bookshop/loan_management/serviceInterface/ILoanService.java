package com.samba_mohamed.bookshop.loan_management.serviceInterface;

import com.samba_mohamed.bookshop.loan_management.model.Loan;

import java.time.LocalDate;
import java.util.List;

public interface ILoanService {
    List<Loan> getLoansByReader(Long readerId);
    List<Loan> getLoansInProcess();
    List<Loan>getNonReturnedLoansByReader(Long readerId);

    List<Loan> getLoansByDateEmprunt(LocalDate date);
    Loan emprunterLivre(Long readerId, String isbn);

    Loan retournerLivreByReaderAndBook(Long readerId, String isbn);


}
