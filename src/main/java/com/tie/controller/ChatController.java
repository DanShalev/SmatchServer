package com.tie.controller;

import com.tie.model.dao.Chat;
import com.tie.model.dto.ChatDTO;
import com.tie.service.ChatService;
import io.github.jav.exposerversdk.PushClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody ChatDTO chatDTO) throws PushClientException, InterruptedException {
        chatService.sendMessage(chatDTO);
    }

    @GetMapping("/get/{groupId}/{userId}")
    public List<Chat> getMessages(@PathVariable String groupId, @PathVariable String userId) {
        return chatService.getMessages(groupId, userId);
    }
}
