package com.tie.controller;

import com.tie.model.dao.Group;
import com.tie.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/addGroup")
    public Group addGroup(@RequestBody Group group) {
        return groupService.addGroup(group);
    }

    @PutMapping("/editGroup")
    public Group editGroup(@RequestBody Group group) {
        return groupService.editGroup(group);
    }

    @GetMapping("/getGroups")
    public List<Group> getGroups() {
        return groupService.getGroups();
    }
}
