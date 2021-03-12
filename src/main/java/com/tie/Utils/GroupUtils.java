package com.tie.Utils;

import com.tie.model.dao.Group;
import com.tie.model.dto.GroupDto;
import com.tie.repository.GroupFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupUtils {

    private final GroupFieldRepository groupFieldRepository;

    public GroupDto convertGroupToGroupDto(Group group) {
        Map<Integer, String> fields = groupFieldRepository.findAllByGroupId(group.getId()).stream()
                .collect(Collectors.toMap(groupField -> groupField.getGroupFieldId().getFieldId(),
                        groupField -> groupField.getGroupFieldId().getFieldName()));
        return new GroupDto(group.getId(), group.getName(), group.getDescription(), group.getNumberOfMembers(), group.getAvatarUrl(), fields);
    }
}
