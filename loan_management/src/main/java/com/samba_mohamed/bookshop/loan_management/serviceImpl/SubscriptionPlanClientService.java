package com.samba_mohamed.bookshop.loan_management.serviceImpl;

import com.samba_mohamed.bookshop.loan_management.dto.Subscription;
import com.samba_mohamed.bookshop.loan_management.dto.SubscriptionPlan;
import com.samba_mohamed.bookshop.loan_management.exception.NoSubscriptionValidFoundForReaderException;
import com.samba_mohamed.bookshop.loan_management.exception.SubscriptionPlanNotFoundException;
import com.samba_mohamed.bookshop.loan_management.serviceInterface.ISubscriptionPlanClientService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SubscriptionPlanClientService implements ISubscriptionPlanClientService {
    @Value("${microservices.plan-service-url}")
    private String subscriptionPlanClientUrl ;
    @Override
    public SubscriptionPlan getSubscriptionPlanById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionPlan subscriptionPlan = restTemplate.getForObject(subscriptionPlanClientUrl + "/subscriptionplans/" + id, SubscriptionPlan.class);

        return subscriptionPlan;
    }
}
