package com.tie.service;

import com.tie.Utils.GroupUtils;
import com.tie.model.dao.Group;
import com.tie.model.dao.Subscription;
import com.tie.model.dao.SubscriptionId;
import com.tie.model.dao.User;
import com.tie.model.dto.GroupDTO;
import com.tie.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {

    private final UserService userService;
    private final GroupService groupService;
    private final SubscriptionRepository subscriptionRepository;
    private final GroupUtils groupUtils;

    public void deleteSubscription(String groupId,String userId) {
        SubscriptionId subscriptionId = new SubscriptionId(groupId, userId);
        Subscription subscription = verifySubscriptionExists(subscriptionId);
        subscriptionRepository.delete(subscription);
    }

    public List<GroupDTO> getGroups(String userId) {
        userService.verifyUserExists(userId);
        return subscriptionRepository.findSubscriptionsByUserId(userId).stream()
                .map(Subscription::getGroup)
                .map(groupUtils::convertGroupToGroupDto)
                .collect(Collectors.toList());
    }

    public Subscription addUserToGroup(String groupId, String userId) {
        SubscriptionId subscriptionId = new SubscriptionId(groupId, userId);
        User user = userService.verifyUserExists(userId);
        Group group = groupService.verifyGroupExists(groupId);
        verifySubscriptionDoesntExist(subscriptionId);
        Subscription subscription = new Subscription(subscriptionId, group, user);
        subscriptionRepository.save(subscription);
        return subscription;
    }

    private Subscription verifySubscriptionExists(SubscriptionId subscriptionId) {
        return subscriptionRepository.findSubscriptionById(subscriptionId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("%s is not subscribed to group %s", subscriptionId.getUserId(),
                                subscriptionId.getGroupId())));
    }

    private void verifySubscriptionDoesntExist(SubscriptionId subscriptionId) {
        subscriptionRepository.findSubscriptionById(subscriptionId).ifPresent(
                sub -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("User %s is already subscribed to group %s.",
                            subscriptionId.getUserId(), subscriptionId.getGroupId()));
                }
        );
    }
}
