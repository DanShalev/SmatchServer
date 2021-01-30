package com.tie.service;

import com.tie.model.dao.Group;
import com.tie.model.dao.GroupField;
import com.tie.model.dto.GroupDto;
import com.tie.repository.GroupFieldRepository;
import com.tie.repository.GroupRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private final GroupFieldRepository groupFieldRepository;

    public GroupDto createGroup(GroupDto groupDto) {
        groupDto.setId(UUID.randomUUID().toString());
        groupDto.getFields().forEach(field -> createField(groupDto.getId(), field));
        groupRepository.save(new Group(groupDto));
        return groupDto;
    }

    private void createField(String groupId, String field) {
        groupFieldRepository.save(new GroupField(groupId, field));
    }

    public GroupDto editGroup(GroupDto group) {
        verifyGroupExists(group.getId());
        groupRepository.save(new Group(group));
        return group;
    }

    public List<GroupDto> getGroups() {
        return groupRepository.findAll().stream().map(this::convertGroupToGroupDto).collect(Collectors.toList());
    }

    private GroupDto convertGroupToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto(group.getId(), group.getName(), group.getDescription());
        List<String> groupFields = groupFieldRepository.findAllByGroupId(group.getId()).stream().map(GroupField::getFieldName).collect(Collectors.toList());
        groupDto.setFields(groupFields);
        return groupDto;
    }

    Group verifyGroupExists(String groupId) {
        return groupRepository.findGroupById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Group %s doesn't exist.", groupId)));
    }
}
