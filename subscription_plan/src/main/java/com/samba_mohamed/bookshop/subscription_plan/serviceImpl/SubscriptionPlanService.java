package com.samba_mohamed.bookshop.subscription_plan.serviceImpl;

import com.samba_mohamed.bookshop.subscription_plan.exception.SubscriptionPlanAlreadyExistsException;
import com.samba_mohamed.bookshop.subscription_plan.exception.SubscriptionPlanNotFoundException;
import com.samba_mohamed.bookshop.subscription_plan.model.SubscriptionPlan;
import com.samba_mohamed.bookshop.subscription_plan.repository.SubscriptionPlanRepository;
import com.samba_mohamed.bookshop.subscription_plan.serviceInterface.ISubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionPlanService implements ISubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public List<SubscriptionPlan> getAllSubscriptionPlans() {
        return subscriptionPlanRepository.findAll();
    }

    @Override
    public SubscriptionPlan getSubscriptionPlanByNom(String nom) {
        return subscriptionPlanRepository.findByNomIgnoreCase(nom)
                .orElseThrow(() -> new SubscriptionPlanNotFoundException("Le plan d'abonnement avec le nom '" + nom + "' est introuvable"));
    }

    @Override
    public List<SubscriptionPlan> createManySubscriptionPlans(List<SubscriptionPlan> subscriptionPlans){
        try{
            return subscriptionPlanRepository.saveAll(subscriptionPlans);
        }catch (DataIntegrityViolationException e) {
            throw new SubscriptionPlanAlreadyExistsException(e.getLocalizedMessage());
        }
    }
    @Override
    public SubscriptionPlan getSubscriptionPlanById(Long id) {
        return subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new SubscriptionPlanNotFoundException("Le plan d'abonnement avec l'id " + id + " est introuvable"));
    }

    @Override
    public SubscriptionPlan createSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        Optional<SubscriptionPlan> s=  subscriptionPlanRepository.findByNomIgnoreCase(subscriptionPlan.getNom());
        if(!s.isPresent()){
            return subscriptionPlanRepository.save(subscriptionPlan);
        }
        throw new SubscriptionPlanAlreadyExistsException("Le nom du plan d'abonnement existe déjà : " + subscriptionPlan.getNom());

    }

    @Override
    public SubscriptionPlan updateSubscriptionPlan(Long id, SubscriptionPlan subscriptionPlanDetails) {
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanById(id);
        String newNom = subscriptionPlanDetails.getNom();
        // Vérifier si le nouveau nom est différent de l'ancien nom
        if (!newNom.equals(subscriptionPlan.getNom())) {
            // Vérifier si le nouveau nom est déjà utilisé pour un autre plan d'abonnement
            Optional<SubscriptionPlan> existingPlan = subscriptionPlanRepository.findByNomIgnoreCase(newNom);
            if (existingPlan.isPresent() && !existingPlan.get().getId().equals(subscriptionPlan.getId())) {
                throw new SubscriptionPlanAlreadyExistsException("Un plan d'abonnement avec le nom " + newNom + " existe déjà");
            }
            subscriptionPlan.setNom(newNom);
        }

        subscriptionPlan.setDureeEnMois(subscriptionPlanDetails.getDureeEnMois());
        subscriptionPlan.setNombreEmpruntParSemaine(subscriptionPlanDetails.getNombreEmpruntParSemaine());

        subscriptionPlan = subscriptionPlanRepository.save(subscriptionPlan);
        return subscriptionPlan;
    }



    @Override
    public void deleteSubscriptionPlan(Long id) {
        SubscriptionPlan subscriptionPlan = getSubscriptionPlanById(id);
        subscriptionPlanRepository.delete(subscriptionPlan);
    }
}
