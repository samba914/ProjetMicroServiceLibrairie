package com.samba_mohamed.bookshop.subscription.serviceImpl;

import com.samba_mohamed.bookshop.subscription.dto.SubscriptionPlan;
import com.samba_mohamed.bookshop.subscription.exception.ReaderNotFoundException;
import com.samba_mohamed.bookshop.subscription.serviceInterface.ISubscriptionPlanClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SubscriptionPlanClient implements ISubscriptionPlanClient {

    @Value("${microservices.plan-service-url}")
    private String subscriptionPlanServiceUrl;

    @Override
    public SubscriptionPlan getSuscriptionPlanById(Long plan_id) {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionPlan plan = restTemplate.getForObject(subscriptionPlanServiceUrl + "/subscriptionplans/" + plan_id, SubscriptionPlan.class);
        if (plan == null) {
            throw new ReaderNotFoundException("Lecteur introuvable pour l'ID : " + plan_id);
        }
        return plan;
    }

    @Override
    public SubscriptionPlan getSuscriptionPlanByNom(String nom) {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionPlan plan = restTemplate.getForObject(subscriptionPlanServiceUrl + "/subscriptionplans/nom/" + nom, SubscriptionPlan.class);
        if (plan == null) {
            throw new ReaderNotFoundException("Lecteur introuvable pour le nom : " + nom);
        }
        return plan;
    }
}
