package com.tie.controller;

import com.tie.model.dao.Subscription;
import com.tie.model.dao.SubscriptionId;
import com.tie.model.dto.GroupDTO;
import com.tie.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/user")
    public List<GroupDTO> getGroups(@RequestHeader String userId) {
        return subscriptionService.getGroups(userId);
    }


    @PostMapping("/add/{groupId}")
    public Subscription addUserToGroup(@PathVariable String groupId, @RequestHeader String userId) {
        return subscriptionService.addUserToGroup(groupId, userId);
    }

    @DeleteMapping("/delete/{groupId}")
    public void deleteUserFromGroup(@PathVariable String groupId, @RequestHeader String userId) {
        subscriptionService.deleteSubscription(groupId, userId);
    }

}
