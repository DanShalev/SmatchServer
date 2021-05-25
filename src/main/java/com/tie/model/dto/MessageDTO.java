package com.tie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {

    private String groupId;
    private String senderId;
    private String receiverId;
    private String content;

}
