package com.samba_mohamed.bookshop.loan_management.controller;

import com.samba_mohamed.bookshop.loan_management.model.Loan;
import com.samba_mohamed.bookshop.loan_management.serviceInterface.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private ILoanService loanService;

    @GetMapping("/reader/{readerId}")
    public List<Loan> getLoansByReader(@PathVariable Long readerId) {
        List<Loan> loans = loanService.getLoansByReader(readerId);
        return loans;
    }

    @GetMapping("/getBydateEmprunt/{date}")
    public List<Loan> getLoansByDateEmprunt(@PathVariable LocalDate date) {
        List<Loan> loans = loanService.getLoansByDateEmprunt(date);
        return loans;
    }

    @GetMapping("/enCours")
    public List<Loan> getLoansInProcess() {
        List<Loan> loans = loanService.getLoansInProcess();
        return loans;
    }
    @GetMapping("/booksNoReturnedByReader/{readerId}")
    public List<Loan> getNonReturnedLoansByReader(@PathVariable Long readerId) {
        List<Loan> loans = loanService.getNonReturnedLoansByReader(readerId);
        return loans;
    }

    @PostMapping("/reader/{readerId}/book/{isbn}")
    public Loan emprunterLivre(@PathVariable Long readerId, @PathVariable String isbn) {
        Loan loan = loanService.emprunterLivre(readerId, isbn);
        return loan;
    }

    @PutMapping("/return/reader/{readerId}/book/{isbn}")
    public Loan retournerLivreByReaderAndBook(@PathVariable Long readerId, @PathVariable String isbn) {
        Loan loan = loanService.retournerLivreByReaderAndBook(readerId, isbn);
        return loan;
    }
}
