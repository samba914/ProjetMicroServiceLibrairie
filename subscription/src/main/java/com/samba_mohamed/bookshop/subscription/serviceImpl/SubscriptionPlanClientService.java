package com.samba_mohamed.bookshop.subscription.serviceImpl;

import com.samba_mohamed.bookshop.subscription.dto.SubscriptionPlan;
import com.samba_mohamed.bookshop.subscription.exception.ReaderNotFoundException;
import com.samba_mohamed.bookshop.subscription.exception.SubscriptionNotFoundException;
import com.samba_mohamed.bookshop.subscription.serviceInterface.ISubscriptionPlanClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SubscriptionPlanClientService implements ISubscriptionPlanClientService {

    @Value("${microservices.plan-service-url}")
    private String subscriptionPlanServiceUrl;
    @Autowired
    private RedisCacheManager cacheManager;

    @Override
    @Cacheable(value = "subscriptionPlan", key = "#plan_id" , cacheManager = "cacheManager")
    public SubscriptionPlan getSuscriptionPlanById(Long plan_id) {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionPlan plan = restTemplate.getForObject(subscriptionPlanServiceUrl + "/subscriptionplans/" + plan_id, SubscriptionPlan.class);
        if (plan == null) {
            throw new SubscriptionNotFoundException("Plan introuvable pour l'ID : " + plan_id);
        }
        return plan;
    }

    @Override
    @Cacheable(value = "subscriptionPlan", key = "#nom" , cacheManager = "cacheManager")
    public SubscriptionPlan getSuscriptionPlanByNom(String nom) {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionPlan plan = restTemplate.getForObject(subscriptionPlanServiceUrl + "/subscriptionplans/nom/" + nom, SubscriptionPlan.class);
        if (plan == null) {
            throw new SubscriptionNotFoundException("Plan introuvable pour le nom : " + nom);
        }
        return plan;
    }
}
