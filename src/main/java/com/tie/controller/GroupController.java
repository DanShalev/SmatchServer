package com.tie.controller;

import com.tie.model.dao.Group;
import com.tie.model.dao.SubscriptionId;
import com.tie.model.dao.User;
import com.tie.service.GroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/")
    public Group addGroup(@RequestBody Group group) {
        return groupService.addGroup(group);
    }

    @PutMapping("/")
    public Group editGroup(@RequestBody Group group) {
        return groupService.editGroup(group);
    }

    @GetMapping("/")
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @DeleteMapping("/")
    public void leaveGroup(@RequestBody SubscriptionId subscriptionId) {
        groupService.leaveGroup(subscriptionId);
    }

    @GetMapping("/{groupId}/users")
    public List<User> getGroupUsers(@PathVariable String groupId) {
        return groupService.getGroupUsers(groupId);
    }

    @PostMapping("/{groupId}/{userId}/addUser")
    public SubscriptionId addUserToGroup(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.addUserToGroup(groupId, userId);
    }
}
