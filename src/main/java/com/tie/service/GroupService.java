package com.tie.service;

import com.tie.model.dao.Group;
import com.tie.model.dao.Subscription;
import com.tie.model.dao.SubscriptionId;
import com.tie.model.dao.User;
import com.tie.repository.GroupRepository;
import com.tie.repository.SubscriptionRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (!isGroupExists(group.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This group doesn't exists");
        }
        groupRepository.save(group);
        return group;
    }

    private boolean isGroupExists(String groupId) {
        return groupRepository.findAll().stream().anyMatch(currGroup -> currGroup.getId().equals(groupId));
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public void leaveGroup(SubscriptionId subscriptionId) {
        Subscription subscription = getSubscriptionById(subscriptionId);
        subscriptionRepository.delete(subscription);
    }

    public List<User> getGroupUsers(String groupId) {
        List<Subscription> groupSubscriptions = subscriptionRepository.findSubscriptionsByGroupId(groupId);
        if (groupSubscriptions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Group Id %s doesn't exists or is empty", groupId));
        }
        return groupSubscriptions.stream().map(Subscription::getUser).collect(Collectors.toList());
    }

    private Subscription getSubscriptionById(SubscriptionId subscriptionId) {
        return subscriptionRepository.findSubscriptionById(subscriptionId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("%s is not subscribed to group %s", subscriptionId.getUserId(),
                                subscriptionId.getGroupId())));
    }
}
