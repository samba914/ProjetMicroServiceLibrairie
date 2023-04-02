package com.samba_mohamed.bookshop.subscription.serviceInterface;

import com.samba_mohamed.bookshop.subscription.dto.SubscriptionPlan;
import com.samba_mohamed.bookshop.subscription.model.Subscription;

import java.util.List;

public interface ISubscriptionService {

    List<Subscription> getAllSubscriptions();

    List<Subscription> getAllSubscriptionsValid();

    Subscription getSubscriptionValidByReaderId(Long id);

    Subscription getSubscriptionById(Long id);

    List<Subscription> getSubscriptionsByReaderId(Long readerId);

    List<Subscription> getSubscriptionsBySubscriptionPlanId(Long subscriptionPlanId);

    Subscription createSubscription(Long readerId, Long subscriptionPlanId);

    Subscription CancelASubscription(Long subscriptionId);

    Subscription getSubscriptionByReaderIdAndPlanId(Long readerId, Long planId);

    void deleteSubscriptionById(Long id);
}

