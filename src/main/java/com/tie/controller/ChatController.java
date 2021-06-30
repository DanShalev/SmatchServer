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

    @GetMapping("/get/{groupId}/{otherUserId}")
    public List<Message> getConversationMessages(@PathVariable String groupId, @PathVariable String otherUserId, @RequestHeader String userId) {
        return chatService.getConversationMessages(groupId, userId, otherUserId);
    }

    @GetMapping("/get")
    public Map<String, Map<String, List<Message>>> getAllSubscribedGroupsMessages(@RequestHeader String userId) {
        return chatService.getAllSubscribedGroupsMessages(userId);
    }
}
