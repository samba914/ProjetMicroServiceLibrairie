package com.samba_mohamed.bookshop.subscription.repository;

import com.samba_mohamed.bookshop.subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByReaderId(Long readerId);

    List<Subscription> findByEndDateGreaterThan(LocalDate now);
    List<Subscription> findBySubscriptionPlanId(Long subscriptionPlanId);


    Optional<Subscription> getSubscriptionByReaderIdAndSubscriptionPlanId(Long readerId, Long SubscriptionPlanId);

    Optional<Subscription> getSubscriptionByReaderIdAndEndDateGreaterThan(Long readerId, LocalDate now);
    List<Subscription> findByReaderIdAndEndDateGreaterThanAndSubscriptionPlanIdNot(Long readerId, LocalDate now, Long planId);
}
