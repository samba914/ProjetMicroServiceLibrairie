package com.samba_mohamed.bookshop.subscription_plan.repository;

import com.samba_mohamed.bookshop.subscription_plan.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    Optional<SubscriptionPlan> findByNomIgnoreCase(String nom);

}