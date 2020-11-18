package com.tie.service;

import com.tie.model.dao.Group;
import com.tie.repository.GroupRepository;

import java.util.List;
import java.util.UUID;

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

    Group verifyGroupExists(String groupId) {
        return groupRepository.findGroupById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Group %s doesn't exist.", groupId)));
    }
}
