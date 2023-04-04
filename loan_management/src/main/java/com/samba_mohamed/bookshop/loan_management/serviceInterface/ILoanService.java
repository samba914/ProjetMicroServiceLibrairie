package com.samba_mohamed.bookshop.loan_management.serviceInterface;

import com.samba_mohamed.bookshop.loan_management.model.Loan;

import java.util.List;

public interface ILoanService {
    List<Loan> getLoansByReader(Long readerId);
    List<Loan> getNonReturnedLoansByBook(String isbn);
    Loan emprunterLivre(Long readerId, String isbn);
    Loan retournerLivreByLoanId(Long loanId);
    Loan retournerLivreByReaderAndBook(Long readerId, String isbn);
}
