package com.samba_mohamed.bookshop.subscription.serviceInterface;

import com.samba_mohamed.bookshop.subscription.dto.SubscriptionPlan;

public interface ISubscriptionPlanClientService {
    public SubscriptionPlan getSuscriptionPlanById(Long reader_id);
    public SubscriptionPlan getSuscriptionPlanByNom(String nom);
}
