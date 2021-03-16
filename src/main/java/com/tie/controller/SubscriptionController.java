package com.tie.controller;

import com.tie.model.dao.Subscription;
import com.tie.model.dao.SubscriptionId;
import com.tie.model.dao.User;
import com.tie.model.dto.GroupDto;
import com.tie.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    @GetMapping("/group/{groupId}")
    public List<User> getGroupProfiles(@PathVariable String groupId) {
        return subscriptionService.getGroupProfiles(groupId);
    }

    @GetMapping("/user/{userId}")
    public List<GroupDto> getGroups(@PathVariable String userId) {
        return subscriptionService.getGroups(userId);
    }


    @PostMapping("/add/{groupId}/{userId}")
    public Subscription addUserToGroup(@PathVariable String groupId, @PathVariable String userId) {
        return subscriptionService.addUserToGroup(new SubscriptionId(groupId, userId));
    }

    @DeleteMapping("/delete/{groupId}/{userId}")
    public void deleteUserFromGroup(@PathVariable String groupId, @PathVariable String userId) {
        subscriptionService.deleteSubscription(new SubscriptionId(groupId, userId));
    }

}
