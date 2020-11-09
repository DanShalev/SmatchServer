package com.tie.controller;

import com.tie.model.dao.SubscriptionId;
import com.tie.model.dao.Group;
import com.tie.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/addGroup")
    public Group addUser(@RequestBody Group group) {
        return groupService.addGroup(group);
    }

    @PutMapping("/editGroup")
    public Group editUser(@RequestBody Group group) {
        return groupService.editGroup(group);
    }

    @GetMapping("/getGroups")
    public List<Group> getUsers() {
        return groupService.getGroups();
    }

    @DeleteMapping("/")
    public void leaveGroup(@RequestBody SubscriptionId subscriptionId) {
        groupService.leaveGroup(subscriptionId);
    }
}
