package com.samba_mohamed.bookshop.subscription_plan.controller;

import com.samba_mohamed.bookshop.subscription_plan.model.SubscriptionPlan;
import com.samba_mohamed.bookshop.subscription_plan.serviceInterface.ISubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptionplans")
public class SubscriptionPlanController {
    @Autowired
    private ISubscriptionPlanService subscriptionPlanService;

    @GetMapping("")
    public List<SubscriptionPlan> getAllSubscriptionPlans() {
        return subscriptionPlanService.getAllSubscriptionPlans();
    }

    @GetMapping("/{id}")
    public SubscriptionPlan getSubscriptionPlanById(@PathVariable Long id) {
        return subscriptionPlanService.getSubscriptionPlanById(id);
    }
    @GetMapping("/nom/{nom}")
    public SubscriptionPlan getSubscriptionPlanByNom(@PathVariable String nom) {
        return subscriptionPlanService.getSubscriptionPlanByNom(nom);
    }

    @PostMapping("")
    public SubscriptionPlan createSubscriptionPlan(@RequestBody SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanService.createSubscriptionPlan(subscriptionPlan);
    }
    @PostMapping("/createmany")
    public List<SubscriptionPlan> createManySubscriptionPlans(@RequestBody List<SubscriptionPlan> subscriptionPlans) {
        return subscriptionPlanService.createManySubscriptionPlans(subscriptionPlans);
    }

    @PutMapping("/{id}")
    public SubscriptionPlan updateSubscriptionPlan(@PathVariable Long id, @RequestBody SubscriptionPlan subscriptionPlanDetails) {
        return subscriptionPlanService.updateSubscriptionPlan(id, subscriptionPlanDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscriptionPlan(@PathVariable Long id) {
        subscriptionPlanService.deleteSubscriptionPlan(id);
    }
}
