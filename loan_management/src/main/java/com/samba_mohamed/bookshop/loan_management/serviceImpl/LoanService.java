package com.samba_mohamed.bookshop.loan_management.serviceImpl;

import com.samba_mohamed.bookshop.loan_management.model.Loan;
import com.samba_mohamed.bookshop.loan_management.repository.LoanRepository;
import com.samba_mohamed.bookshop.loan_management.serviceInterface.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService implements ILoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<Loan> getLoansByReader(Long readerId) {
        return loanRepository.findByReaderId(readerId);
    }

    @Override
    public List<Loan> getNonReturnedLoansByBook(String isbn) {
        return loanRepository.findByIsbnAndDateRetourIsNull(isbn);
    }

    @Override
    public Loan emprunterLivre(Long readerId, String isbn) {
        return null;
    }

    @Override
    public Loan retournerLivreByLoanId(Long loanId) {
        return null;
    }

    @Override
    public Loan retournerLivreByReaderAndBook(Long readerId, String isbn) {
        return null;
    }
}
