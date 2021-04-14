package com.tie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatDTO {

    private String groupId;
    private String senderId;
    private String receiverId;
    private String message;

}
