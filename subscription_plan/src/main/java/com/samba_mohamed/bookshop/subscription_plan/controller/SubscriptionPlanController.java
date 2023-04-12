package com.samba_mohamed.bookshop.subscription_plan.controller;

import com.samba_mohamed.bookshop.subscription_plan.model.SubscriptionPlan;
import com.samba_mohamed.bookshop.subscription_plan.serviceInterface.ISubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptionplans")
public class SubscriptionPlanController {
    @Autowired
    private ISubscriptionPlanService subscriptionPlanService;

    @GetMapping("")
    @Cacheable(value = "subscription_plans", key = "#root.methodName")
    public List<SubscriptionPlan> getAllSubscriptionPlans() {
        return subscriptionPlanService.getAllSubscriptionPlans();
    }

    @Cacheable(value="subscription_plans", key="#id")
    @GetMapping("/{id}")
    public SubscriptionPlan getSubscriptionPlanById(@PathVariable Long id) {
        return subscriptionPlanService.getSubscriptionPlanById(id);
    }
    @GetMapping("/nom/{nom}")
    public SubscriptionPlan getSubscriptionPlanByNom(@PathVariable String nom) {
        return subscriptionPlanService.getSubscriptionPlanByNom(nom);
    }

    @PostMapping("")
    @Cacheable(value="subscription_plans", key="#result.id")
    public SubscriptionPlan createSubscriptionPlan(@RequestBody SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanService.createSubscriptionPlan(subscriptionPlan);
    }
    @PostMapping("/createmany")
    @CacheEvict(value="subscrption_plans")
    public List<SubscriptionPlan> createManySubscriptionPlans(@RequestBody List<SubscriptionPlan> subscriptionPlans) {
        return subscriptionPlanService.createManySubscriptionPlans(subscriptionPlans);
    }

    @PutMapping("/{id}")
    @CachePut(value="subscription_plans", key="#id")
    public SubscriptionPlan updateSubscriptionPlan(@PathVariable Long id, @RequestBody SubscriptionPlan subscriptionPlanDetails) {
        return subscriptionPlanService.updateSubscriptionPlan(id, subscriptionPlanDetails);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "subscription_plans", key = "#id")
    public void deleteSubscriptionPlan(@PathVariable Long id) {
        subscriptionPlanService.deleteSubscriptionPlan(id);
    }
}
