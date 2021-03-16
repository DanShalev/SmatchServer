package com.tie.service;

import com.tie.Utils.GroupUtils;
import com.tie.model.dao.Group;
import com.tie.model.dao.GroupField;
import com.tie.model.dao.GroupFieldId;
import com.tie.model.dto.GroupDto;
import com.tie.repository.GroupFieldRepository;
import com.tie.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupFieldRepository groupFieldRepository;
    private final GroupUtils groupUtils;

    public GroupDto createGroup(GroupDto groupDto) {
        groupDto.setId(UUID.randomUUID().toString());
        groupRepository.save(new Group(groupDto));
        groupDto.getFields().forEach((fieldId, fieldName) -> createField(groupDto.getId(), fieldId, fieldName));
        return groupDto;
    }

    private void createField(String groupId, Integer fieldId, String fieldName) {
        Group group = verifyGroupExists(groupId);
        groupFieldRepository.save(new GroupField(new GroupFieldId(groupId, fieldId, fieldName), group));
    }

    public GroupDto editGroup(GroupDto group) {
        verifyGroupExists(group.getId());
        groupRepository.save(new Group(group));
        return group;
    }

    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream().map(groupUtils::convertGroupToGroupDto
        ).collect(Collectors.toList());
    }

    Group verifyGroupExists(String groupId) {
        return groupRepository.findGroupById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Group %s doesn't exist.", groupId)));
    }
}
