package com.tie.model.dao;

import com.tie.model.dto.ChatDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "chats")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chat {

    public Chat(ChatDTO chatDTO) {
        this.chatId = new ChatId(chatDTO.getGroupId(), chatDTO.getSenderId(), chatDTO.getReceiverId());
        this.time = new Date().getTime();
        this.message = chatDTO.getMessage();
    }

    @EmbeddedId
    private ChatId chatId;

    @Column(name = "time")
    private Long time;

    @Column(name = "message", nullable = false)
    private String message;

}
