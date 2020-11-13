package com.tie.repository;


import com.tie.model.dao.Subscription;
import com.tie.model.dao.SubscriptionId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Optional<Subscription> findSubscriptionById(SubscriptionId subscriptionId);

    List<Subscription> findSubscriptionsByGroupId(String groupId);

    List<Subscription> findSubscriptionsByUserId(String userId);
}