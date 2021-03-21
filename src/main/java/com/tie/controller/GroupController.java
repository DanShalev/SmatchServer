package com.tie.controller;

import com.tie.model.dao.User;
import com.tie.model.dto.GroupDto;
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
    public List<GroupDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/create")
    public GroupDto createGroup(@RequestBody GroupDto group) {
        return groupService.createGroup(group);
    }

    @PutMapping("/edit")
    public GroupDto editGroup(@RequestBody GroupDto group) {
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
}
