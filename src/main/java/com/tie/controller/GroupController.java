package com.tie.controller;

import com.tie.model.dto.GroupDto;
import com.tie.service.GroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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


}
