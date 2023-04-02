package com.samba_mohamed.bookshop.subscription.serviceImpl;

import com.samba_mohamed.bookshop.subscription.dto.Reader;
import com.samba_mohamed.bookshop.subscription.dto.SubscriptionPlan;
import com.samba_mohamed.bookshop.subscription.exception.SubscriptionNotFoundException;
import com.samba_mohamed.bookshop.subscription.model.Subscription;
import com.samba_mohamed.bookshop.subscription.repository.SubscriptionRepository;
import com.samba_mohamed.bookshop.subscription.serviceInterface.IReaderClient;
import com.samba_mohamed.bookshop.subscription.serviceInterface.ISubscriptionPlanClient;
import com.samba_mohamed.bookshop.subscription.serviceInterface.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService  implements ISubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private IReaderClient readerClient;
    @Autowired
    private ISubscriptionPlanClient subscriptionPlanClient;


    public List<Subscription> getAllSubscriptions(){
        return subscriptionRepository.findAll();
    }

    @Override
    public List<Subscription> getAllSubscriptionsValid() {
        return subscriptionRepository.findByEndDateGreaterThan(LocalDate.now());
    }

    @Override
    public Subscription getSubscriptionValidByReaderId(Long id) {
        return subscriptionRepository.getSubscriptionByReaderIdAndEndDateGreaterThan(id,LocalDate.now()).orElseThrow(() -> new SubscriptionNotFoundException("Impossible de trouver l'abonnement avec l'id: " + id));
    }

    public Subscription getSubscriptionById(Long id)  {
        return subscriptionRepository.findById(id).orElseThrow(() -> new SubscriptionNotFoundException("Impossible de trouver l'abonnement avec l'id: " + id));
    }

    public List<Subscription> getSubscriptionsByReaderId(Long readerId) {
        return subscriptionRepository.findByReaderId(readerId);
    }

    public List<Subscription> getSubscriptionsBySubscriptionPlanId(Long subscriptionPlanId) {
        return subscriptionRepository.findBySubscriptionPlanId(subscriptionPlanId);
    }


    public Subscription createSubscription(Long readerId, Long subscriptionPlanId){
        LocalDate startDate = LocalDate.now();
        SubscriptionPlan subscriptionPlan = subscriptionPlanClient.getSuscriptionPlanById(subscriptionPlanId);
        //check s'il y avait un abonnement existant pour ce lecteur et ce plan
        // si oui on mettra a jour la date de fin
        Subscription subscription = null;
        subscription = getSubscriptionByReaderIdAndPlanId(readerId,subscriptionPlanId);
        if(subscription == null){
            Reader reader = readerClient.getReaderById(readerId);
            subscription = new Subscription();
            subscription.setReaderId(reader.getId());
            subscription.setSubscriptionPlanId(subscriptionPlan.getId());
            subscription.setStartDate(startDate);
            subscription.setEndDate(startDate.plusMonths(subscriptionPlan.getDureeEnMois()));
        }else{
            if(subscription.getEndDate().compareTo(LocalDate.now()) > 0 ){
                startDate = subscription.getEndDate() ;
            }
            subscription.setEndDate(startDate.plusMonths(subscriptionPlan.getDureeEnMois()));
        }
        //s'il existe un autre abonnement valide pour un autre plan , il faut mettre fin à cette abonnement
        // car un utilisateur doit avoir qu'in abonnment valide, Normalement il y en a que un mais par precaution on recupere une liste

        List<Subscription> list = subscriptionRepository.findByReaderIdAndEndDateGreaterThanAndSubscriptionPlanIdNot(
                readerId, LocalDate.now(), subscriptionPlanId);
        for(Subscription s : list){
            s.setEndDate(LocalDate.now());
        }
        subscriptionRepository.saveAll(list);


        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription CancelASubscription(Long subscriptionId)  {
        Subscription existingSubscription = getSubscriptionById(subscriptionId);
        //si l'abonnement est fini cela veut dire qu'il prend fin aujourd'hui
        existingSubscription.setEndDate(LocalDate.now());
        return subscriptionRepository.save(existingSubscription);
    }

    @Override
    public Subscription getSubscriptionByReaderIdAndPlanId(Long readerId, Long planId) {
        return subscriptionRepository.getSubscriptionByReaderIdAndSubscriptionPlanId(readerId,planId).orElse(null);
    }

    @Override
    public void deleteSubscriptionById(Long id) {
        subscriptionRepository.delete(getSubscriptionById(id));
    }



}
