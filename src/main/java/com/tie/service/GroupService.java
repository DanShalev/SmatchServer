package com.tie.service;

import com.tie.Utils.GroupUtils;
import com.tie.model.dao.*;
import com.tie.model.dto.GroupDTO;
import com.tie.repository.GroupFieldRepository;
import com.tie.repository.GroupRepository;
import com.tie.repository.MatchRepository;
import com.tie.repository.SubscriptionRepository;
import java.util.Base64;
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
    private final MatchRepository matchRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final MatchService matchService;
    private final GroupUtils groupUtils;

    public GroupDTO createGroup(GroupDTO groupDto) {
        groupDto.setId(UUID.randomUUID().toString());
        groupRepository.save(new Group(groupDto));
        groupDto.getFields().forEach((fieldId, fieldName) -> createField(groupDto.getId(), fieldId, fieldName));
        return groupDto;
    }

    private void createField(String groupId, Integer fieldId, String fieldName) {
        Group group = verifyGroupExists(groupId);
        groupFieldRepository.save(new GroupField(new GroupFieldId(groupId, fieldId, fieldName), group));
    }

    public GroupDTO editGroup(GroupDTO group) {
        verifyGroupExists(group.getId());
        groupRepository.save(new Group(group));
        return group;
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream().map(groupUtils::convertGroupToGroupDto
        ).collect(Collectors.toList());
    }

    Group verifyGroupExists(String groupId) {
        return groupRepository.findGroupById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Group %s doesn't exist.", groupId)));
    }

    public List<User> getGroupProfiles(String groupId, String userId) {
        verifyGroupExists(groupId);

        return subscriptionRepository.findSubscriptionsByGroupId(groupId)
                .stream()
                .map(Subscription::getUser).peek(this::setUserImages)
                .filter(user -> !user.getId().equals(userId))
                .filter(otherUser -> !matchService.didLikeOrDislikeOtherUser(groupId, userId, otherUser.getId()))
                .collect(Collectors.toList());
    }

    public List<String> getGroupMatches(String groupId, String userId) {
        verifyGroupExists(groupId);
        return matchRepository.findAllMatchesByGroupAndUserId(groupId, userId)
                .stream()
                .map(match -> getOtherUserId(match, userId))
                .collect(Collectors.toList());
    }

    private String getOtherUserId(Match match, String userId) {
        String firstUser = match.getMatchId().getFirstUserId();
        String secondUser = match.getMatchId().getSecondUserId();
        return firstUser.equals(userId) ? secondUser : firstUser;
    }

    private void setUserImages(User user) {
        if (user.getImage1() != null) {
            user.setImage1(decode(user.getImage1()));
        }
        if (user.getImage2() != null) {
            user.setImage2(decode(user.getImage2()));
        }
        if (user.getImage3() != null) {
            user.setImage3(decode(user.getImage3()));
        }
    }

    private static byte[] decode(byte[] image) {
        return Base64.getDecoder().decode(image);
    }
}
