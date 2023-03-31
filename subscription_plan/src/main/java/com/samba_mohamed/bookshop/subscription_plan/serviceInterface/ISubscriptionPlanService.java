package com.samba_mohamed.bookshop.subscription_plan.serviceInterface;

import com.samba_mohamed.bookshop.subscription_plan.model.SubscriptionPlan;

import java.util.List;

public interface ISubscriptionPlanService {
    List<SubscriptionPlan> getAllSubscriptionPlans();

    List<SubscriptionPlan> createManySubscriptionPlans(List<SubscriptionPlan> subscriptionPlans );
    SubscriptionPlan getSubscriptionPlanById(Long id);
    SubscriptionPlan getSubscriptionPlanByNom(String nom);
    SubscriptionPlan createSubscriptionPlan(SubscriptionPlan subscriptionPlan);
    SubscriptionPlan updateSubscriptionPlan(Long id, SubscriptionPlan subscriptionPlanDetails);
    void deleteSubscriptionPlan(Long id);
}
