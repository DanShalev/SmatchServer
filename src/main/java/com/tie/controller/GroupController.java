package com.tie.controller;

import com.tie.model.dao.SubscriptionId;
import com.tie.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @DeleteMapping("/")
    public void leaveGroup(@RequestBody SubscriptionId subscriptionId) {
        groupService.leaveGroup(subscriptionId);
    }
}
