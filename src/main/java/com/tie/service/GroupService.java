package com.tie.service;

import com.tie.model.dao.Subscription;
import com.tie.model.dao.SubscriptionId;
import com.tie.repository.GroupRepository;
import com.tie.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService {

    private final GroupRepository groupRepository;
    private final SubscriptionRepository subscriptionRepository;

    public void leaveGroup(SubscriptionId subscriptionId) {
        Subscription subscription = getSubscriptionById(subscriptionId);
        subscriptionRepository.delete(subscription);
    }

    private Subscription getSubscriptionById(SubscriptionId subscriptionId) {
        Optional<Subscription> subscription = subscriptionRepository.findAll()
                .stream()
                .filter(currentSubscription -> currentSubscription.getId().equals(subscriptionId))
                .findAny();
        return subscription.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not subscribed to group"));
    }
}
