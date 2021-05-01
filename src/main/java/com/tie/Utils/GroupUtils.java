package com.tie.Utils;

import com.tie.model.dao.Group;
import com.tie.model.dto.GroupDTO;
import com.tie.repository.GroupFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupUtils {

    private final GroupFieldRepository groupFieldRepository;

    public GroupDTO convertGroupToGroupDto(Group group) {
        Map<Integer, String> fields = groupFieldRepository.findAllByGroupId(group.getId()).stream()
                .collect(Collectors.toMap(groupField -> groupField.getGroupFieldId().getFieldId(),
                        groupField -> groupField.getGroupFieldId().getFieldName()));
        return new GroupDTO(group.getId(), group.getName(), group.getDescription(), group.getNumberOfMembers(), group.getAvatar(), fields);
    }
}
