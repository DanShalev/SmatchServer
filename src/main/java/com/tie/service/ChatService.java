package com.tie.service;

import com.tie.model.dao.Chat;
import com.tie.model.dao.User;
import com.tie.model.dto.ChatDTO;
import com.tie.repository.ChatRepository;
import io.github.jav.exposerversdk.PushClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;

    public void sendMessage(ChatDTO chatDTO) throws PushClientException, InterruptedException {
        if (chatDTO.getSenderId().equals(chatDTO.getReceiverId()) || chatDTO.getMessage() == null
                    || chatDTO.getMessage().isBlank() || chatDTO.getGroupId() == null || chatDTO.getGroupId()
                .isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Bad request - groupId: %s, senderId: %s, receiverId: %s, message: %s",
                            chatDTO.getGroupId(), chatDTO.getSenderId(), chatDTO.getReceiverId(),
                            chatDTO.getMessage()));
        }

        Chat chat = new Chat(chatDTO);

        User receiver = userService.verifyUserExists(chat.getChatId().getReceiverId());
        String token = receiver.getPushNotificationToken();
        String title = "Smatch";
        String message = receiver.getName() + " sent you a new message";

        NotificationService.push(token, title, message);
        chatRepository.save(chat);
    }

    public List<Chat> getMessages(String groupId, String userId) {
        if (userId == null || groupId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Bad request - groupId: %s, userId: %s", groupId, userId));
        }

        List<User> senders = userService.getUsers();

        return senders.stream()
                .map(sender -> chatRepository.findAllChatsByGroupAndUserId(groupId, userId, sender.getId()))
                .filter(senderChats -> !senderChats.isEmpty())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
