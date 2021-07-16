package com.tie.service;

import com.tie.Utils.GroupUtils;
import com.tie.model.dao.*;
import com.tie.model.dto.GroupDTO;
import com.tie.repository.GroupFieldRepository;
import com.tie.repository.GroupRepository;
import com.tie.repository.MatchRepository;
import com.tie.repository.SubscriptionRepository;
import com.tie.repository.UserRepository;
import com.tie.model.dto.UserFieldDTO;
import com.tie.repository.*;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.List;
import java.util.Map;
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
    private final UserFieldRepository userFieldRepository;
    private final UserRepository userRepository;
    private final MatchService matchService;
    private final GroupUtils groupUtils;

    public GroupDTO createGroup(GroupDTO groupDto, String userId) {
        groupDto.setId(UUID.randomUUID().toString());
        Group group = new Group(groupDto);
        User user = verifyUserExists(userId);
        SubscriptionId id = new SubscriptionId(groupDto.getId(), userId);
        groupRepository.save(group);
        subscriptionRepository.save(new Subscription(id, group, user));
        groupDto.getFields().forEach((fieldId, fieldName) -> createField(groupDto.getId(), fieldId, fieldName));
        return groupDto;
    }

    private User verifyUserExists(String userId) {
        return userRepository.findUserById(userId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            String.format("User %s doesn't exist.", userId)));
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

    public GroupDTO getGroupById(String groupId, String userId) {
        verifyUserExists(userId);
        Group group = verifyGroupExists(groupId);
        return groupUtils.convertGroupToGroupDto(group);
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
                .map(Subscription::getUser)
                .map(this::setUserImages)
                .filter(user -> !user.getId().equals(userId))
                .filter(otherUser -> !matchService.didLikeOrDislikeOtherUser(groupId, userId, otherUser.getId()))
                .collect(Collectors.toList());
    }

    public List<User> getGroupMatches(String groupId, String userId) {
        verifyGroupExists(groupId);
        return matchRepository.findAllMatchesByGroupAndUserId(groupId, userId)
                .stream()
                .map(match -> getOtherUserId(match, userId))
                .map(this::setUserImages)
                .collect(Collectors.toList());
    }

    private User getOtherUserId(Match match, String userId) {
        String firstUserId = match.getMatchId().getFirstUserId();
        String secondUserId = match.getMatchId().getSecondUserId();
        User secondUser = userRepository.findUserById(secondUserId).orElseThrow();
        User firstUser = userRepository.findUserById(firstUserId).orElseThrow();
        return firstUserId.equals(userId) ? secondUser : firstUser;
    }


    public Map<String, List<User>> getAllSubscribedGroupsProfiles(String userId) {
        return subscriptionRepository.findSubscriptionsByUserId(userId).stream()
                .map(subscription -> subscription.getId().getGroupId())
                .map(groupId -> Pair.of(groupId, getGroupProfiles(groupId, userId)))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    public Map<String, List<User>> getAllSubscribedGroupsMatches(String userId) {
        return subscriptionRepository.findSubscriptionsByUserId(userId)
                .stream()
                .map(subscription -> subscription.getId().getGroupId())
                .map(groupId -> Pair.of(groupId, getGroupMatches(groupId, userId)))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    public List<UserFieldDTO> getUserGroupFields(String groupId, String userId) {
        verifyGroupExists(groupId);

        User user = userRepository.findUserById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("User %s doesn't exist.", userId)));

        UserFieldDTO ageField = new UserFieldDTO("Sex", user.getSex());
        UserFieldDTO sexField = new UserFieldDTO("Age", user.getAge());
        List<UserFieldDTO> result = new ArrayList<>(Arrays.asList(ageField, sexField));

        List<UserField> fields = userFieldRepository.findAllByUserIdAndGroupId(userId, groupId);

        for (UserField field : fields) {
            Optional<GroupField> groupFieldOptional = groupFieldRepository.findByGroupIdAndFieldId(
                    groupId, field.getUserFieldId().getGroupFieldId());
            groupFieldOptional.ifPresent(groupField -> result.add(
                    new UserFieldDTO(groupField.getGroupFieldId().getFieldName(), field.getFieldValue()))
            );
        }
        return result;
    }

    private User setUserImages(User user) {
        if (user.getImage1() != null) {
            user.setImage1(user.getImage1());
        }
        if (user.getImage2() != null) {
            user.setImage2(user.getImage2());
        }
        if (user.getImage3() != null) {
            user.setImage3(user.getImage3());
        }
        return user;
    }

//    private static byte[] decode(byte[] image) {
//        return Base64.getDecoder().decode(image);
//    }
}
