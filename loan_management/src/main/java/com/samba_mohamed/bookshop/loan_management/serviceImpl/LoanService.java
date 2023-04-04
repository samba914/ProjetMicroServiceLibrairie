package com.samba_mohamed.bookshop.loan_management.serviceImpl;

import com.samba_mohamed.bookshop.loan_management.dto.Book;
import com.samba_mohamed.bookshop.loan_management.dto.Reader;
import com.samba_mohamed.bookshop.loan_management.dto.Subscription;
import com.samba_mohamed.bookshop.loan_management.dto.SubscriptionPlan;
import com.samba_mohamed.bookshop.loan_management.exception.*;
import com.samba_mohamed.bookshop.loan_management.model.Loan;
import com.samba_mohamed.bookshop.loan_management.repository.LoanRepository;
import com.samba_mohamed.bookshop.loan_management.serviceInterface.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class LoanService implements ILoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    IReaderClientService readerService ;

    @Autowired
    IBookClientService bookClientService ;
    @Autowired
    ISubscriptionPlanClientService subscriptionPlanClientService ;
    @Autowired
    ISubsciptionClientService subsciptionClientService ;
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
        Reader reader = readerService.getReaderById(readerId);
        if (reader == null) {
            throw new ReaderNotFoundException("Lecteur introuvable pour l'ID : " + readerId);
        }
        Book book = bookClientService.getBookByIsbn(isbn) ;
        if (book == null) {

            throw new BookNotFoundException("Book introuvable pour l'Isbn : " + isbn);
        }
        else if (!book.isDisponible()){
            throw new BookNotAvailableException("Le livre n'est pas disponible pour emprunt.");
        }
        Subscription subscription = subsciptionClientService.getSubscriptionValidByReaderId(readerId) ;
        if (subscription == null){
            throw new NoSubscriptionValidFoundForReaderException("Aucun abonnement valide à été trouvé pour le idReader : " + readerId);
        }
        LocalDate start = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate end = LocalDate.now();
        Long nbEmpruntEffectue =  loanRepository.countByDatePretBetween(start,end);
        SubscriptionPlan subscriptionPlan = subscriptionPlanClientService.getSubscriptionPlanById(subscription.getSubscriptionPlanId()) ;
        if (subscriptionPlan == null) {
            throw new SubscriptionPlanNotFoundException("Une erreur s'est produite lors de la récupération du plan de souscription ");
        }
        int nombreEmpruntParSemaine = subscriptionPlan.getNombreEmpruntParSemaine() ;
        if (nbEmpruntEffectue >= nombreEmpruntParSemaine ){
            throw new LoanNumberExceedException("Le nombre d'emprunt autorisé qui est de : "+nombreEmpruntParSemaine + " a été atteint !") ;
        }
        Loan loan = new Loan();
        loan.setIsbn(book.getIsbn());
        loan.setReaderId(readerId);
        loan.setDatePret(LocalDate.now());
        return loanRepository.save(loan);
    }

    @Override
    public Loan retournerLivreByReaderAndBook(Long readerId, String isbn) {
        Loan loan = loanRepository.findByReaderIdAndIsbnAndDateRetourIsNull(readerId, isbn).orElse(null);
        if (loan == null) {
            throw new LoanException("Il n'existe pas d'emprunt en cours pour le livre :"+ " isbn "+" pour le reader : "+readerId);
        }
        loan.setDateRetour(LocalDate.now());
        return loanRepository.save(loan);
    }
}
