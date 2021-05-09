package com.tie.controller;

import com.tie.model.dto.ChatDTO;
import com.tie.service.ChatService;
import io.github.jav.exposerversdk.PushClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody ChatDTO chatDTO) throws PushClientException, InterruptedException {
        chatService.sendMessage(chatDTO);
    }

}
