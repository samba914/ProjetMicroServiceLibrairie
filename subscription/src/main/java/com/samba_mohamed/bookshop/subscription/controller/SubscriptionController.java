package com.samba_mohamed.bookshop.subscription.controller;

import com.samba_mohamed.bookshop.subscription.model.Subscription;
import com.samba_mohamed.bookshop.subscription.serviceInterface.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private ISubscriptionService subscriptionService;

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }
    @GetMapping("/valid")
    public List<Subscription> getAllSubscriptionsValid() {
        return subscriptionService.getAllSubscriptionsValid();
    }
    @GetMapping("/{id}")
    public Subscription getSubscriptionById(@PathVariable("id") Long id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @GetMapping("/reader/{readerId}")
    public List<Subscription> getSubscriptionsByReaderId(@PathVariable("readerId") Long readerId) {
        return subscriptionService.getSubscriptionsByReaderId(readerId);
    }
    @GetMapping("/reader/valid/{readerId}")
    public Subscription getSubscriptionValidByReaderId(@PathVariable("readerId") Long readerId) {
        return subscriptionService.getSubscriptionValidByReaderId(readerId);
    }

    @GetMapping("/plan/{subscriptionPlanId}")
    public List<Subscription> getSubscriptionsBySubscriptionPlanId(@PathVariable("subscriptionPlanId") Long subscriptionPlanId) {
        return subscriptionService.getSubscriptionsBySubscriptionPlanId(subscriptionPlanId);
    }

    @PostMapping("/{readerId}/{subscriptionPlanId}")
    public Subscription createSubscription(@PathVariable("readerId") Long readerId, @PathVariable("subscriptionPlanId") Long subscriptionPlanId) {
        return subscriptionService.createSubscription(readerId, subscriptionPlanId);
    }

    @PutMapping("/cancel/{id}")
    public Subscription updateSubscription(@PathVariable("id") Long subscriptionId) {
        return subscriptionService.CancelASubscription(subscriptionId);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscriptionById(@PathVariable("id") Long id) {
        subscriptionService.deleteSubscriptionById(id);
    }

}
