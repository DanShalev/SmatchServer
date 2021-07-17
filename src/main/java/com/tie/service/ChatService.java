package com.tie.service;

import com.tie.model.dao.Message;
import com.tie.model.dao.User;
import com.tie.model.dto.GroupDTO;
import com.tie.model.dto.MessageDTO;
import com.tie.repository.ChatRepository;
import io.github.jav.exposerversdk.ExpoPushMessage;
import io.github.jav.exposerversdk.PushClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    public void sendMessage(MessageDTO messageDTO) throws PushClientException, InterruptedException {
        if (messageDTO.getSenderId().equals(messageDTO.getReceiverId()) || messageDTO.getContent() == null
                || messageDTO.getContent().isBlank() || messageDTO.getGroupId() == null || messageDTO.getGroupId()
                .isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Bad request - groupId: %s, senderId: %s, receiverId: %s, message: %s",
                            messageDTO.getGroupId(), messageDTO.getSenderId(), messageDTO.getReceiverId(),
                            messageDTO.getContent()));
        }

        Message message = new Message(messageDTO);

        User receiver = userService.verifyUserExists(message.getMessageId().getReceiverId());
        User sender = userService.verifyUserExists(message.getMessageId().getSenderId());

        String token = receiver.getPushNotificationToken();
        String title = "Smatch";
        String messageBody = sender.getName() + " sent you a new message";

        ExpoPushMessage expoPushMessage = NotificationService.createPushMessage(token, title, messageBody, message.getMessageId());
        NotificationService.push(expoPushMessage);
        chatRepository.save(message);
    }

    public List<Message> getConversationMessages(String groupId, String userId, String otherUserId) {
        if (userId == null || otherUserId == null || groupId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Bad request - groupId: %s, userId: %s, otherUserId:%s", groupId, userId, otherUserId));
        }

        List<Message> messages = chatRepository.findAllChatsByGroupAndUserId(groupId, userId, otherUserId);
        chatRepository.deleteAllChatsByGroupAndUserId(groupId, userId, otherUserId);
        return messages;
    }

    public Map<String, Map<String, List<Message>>> getAllSubscribedGroupsMessages(String userId) {
        userService.verifyUserExists(userId);
        return subscriptionService.getGroups(userId).
                stream()
                .collect(Collectors.toMap(GroupDTO::getId, group -> subscriptionService.geUsers(group.getId())
                        .stream().filter(user -> !user.getId().equals(userId))
                        .collect(Collectors.toMap(User::getId, user -> getConversationMessages(group.getId(), userId, user.getId())))
                        .entrySet()
                        .stream()
                        .filter(map -> !map.getValue().isEmpty())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));
    }
}
