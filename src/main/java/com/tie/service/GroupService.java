package com.tie.service;

import com.tie.model.dao.Group;
import com.tie.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        groupRepository.save(group);
        return group;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
}
