package com.tie.repository;


import com.tie.model.dao.Subscription;
import com.tie.model.dao.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Optional<Subscription> findSubscriptionById(SubscriptionId subscriptionId);
}