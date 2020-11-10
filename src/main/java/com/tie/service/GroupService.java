package com.tie.service;

import com.tie.model.dao.Group;
import com.tie.model.dao.Subscription;
import com.tie.model.dao.SubscriptionId;
import com.tie.repository.GroupRepository;
import com.tie.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService {

    private final GroupRepository groupRepository;
    private final SubscriptionRepository subscriptionRepository;

    public Group addGroup(Group group) {
        group.setId(UUID.randomUUID().toString());
        groupRepository.save(group);
        return group;
    }

    public Group editGroup(Group group) {
        if (isGroupExists(group.getId()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This group already exists");
        groupRepository.save(group);
        return group;
    }

    private boolean isGroupExists(String groupId) {
        Optional<Group> group = groupRepository.findAll().stream().filter(currGroup -> currGroup.getId().equals(groupId)).findAny();
        return group.isPresent();
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public void leaveGroup(SubscriptionId subscriptionId) {
        Subscription subscription = getSubscriptionById(subscriptionId);
        subscriptionRepository.delete(subscription);
    }

    private Subscription getSubscriptionById(SubscriptionId subscriptionId) {
        return subscriptionRepository.findSubscriptionById(subscriptionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not subscribed to group"));
    }
}
