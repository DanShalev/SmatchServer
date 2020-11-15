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
    private final UserService userService;

    public Group addGroup(Group group) {
        group.setId(UUID.randomUUID().toString());
        groupRepository.save(group);
        return group;
    }

    public Group editGroup(Group group) {
        verifyGroupExists(group.getId());
        groupRepository.save(group);
        return group;
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

    private void verifySubscriptionDoesntExist(SubscriptionId subscriptionId) {
        subscriptionRepository.findSubscriptionById(subscriptionId).ifPresent(
                sub -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("User %s is already subscribed to group %s.",
                                                                                                 subscriptionId.getUserId(), subscriptionId.getGroupId())); }
        );
    }

    private Group verifyGroupExists(String groupId) {
        return groupRepository.findGroupById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Group %s doesn't exist.", groupId)));
    }

    public SubscriptionId addUserToGroup(String groupId, String userId) {
        User user = userService.verifyUserExists(userId);
        Group group = verifyGroupExists(groupId);

        SubscriptionId subscriptionId = new SubscriptionId(userId, groupId);
        verifySubscriptionDoesntExist(subscriptionId);

        Subscription subs = new Subscription(subscriptionId, group, user);
        subscriptionRepository.save(subs);
        return subscriptionId;
    }
}
