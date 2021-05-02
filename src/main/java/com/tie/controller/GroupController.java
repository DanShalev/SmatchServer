package com.tie.controller;

import com.tie.model.dao.User;
import com.tie.model.dto.GroupDTO;
import com.tie.model.dto.UserFieldDTO;
import com.tie.service.GroupService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/get")
    public List<GroupDTO> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/create")
    public GroupDTO createGroup(@RequestBody GroupDTO group) {
        return groupService.createGroup(group);
    }

    @PutMapping("/edit")
    public GroupDTO editGroup(@RequestBody GroupDTO group) {
        return groupService.editGroup(group);
    }

    @GetMapping("/{groupId}/{userId}")
    public List<User> getGroupProfiles(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.getGroupProfiles(groupId, userId);
    }

    @GetMapping("/matches/{groupId}/{userId}")
    public List<String> getGroupMatches(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.getGroupMatches(groupId, userId);
    }

    @GetMapping("/fields/{groupId}/{userId}")
    List<UserFieldDTO> getUserGroupFields(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.getUserGroupFields(groupId, userId);
    }
}
