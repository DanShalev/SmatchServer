package com.tie.controller;

import com.tie.model.dao.Message;
import com.tie.model.dto.MessageDTO;
import com.tie.service.ChatService;
import io.github.jav.exposerversdk.PushClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageDTO messageDTO) throws PushClientException, InterruptedException {
        chatService.sendMessage(messageDTO);
    }

    @GetMapping("/get/{groupId}/{userId}/{otherUserId}")
    public List<Message> getConversationMessages(@PathVariable String groupId, @PathVariable String userId, @PathVariable String otherUserId) {
        return chatService.getConversationMessages(groupId, userId, otherUserId);
    }

    @GetMapping("/get/{userId}")
    public Map<String, Map<String, List<Message>>> getAllSubscribedGroupsMessages(@PathVariable String userId) {
        return chatService.getAllSubscribedGroupsMessages(userId);
    }
}
