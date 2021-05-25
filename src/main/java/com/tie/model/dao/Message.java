package com.tie.model.dao;

import com.tie.model.dto.MessageDTO;
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
public class Message {

    @EmbeddedId
    private MessageId messageId;

    public Message(MessageDTO messageDTO) {
        this.messageId = new MessageId(messageDTO.getGroupId(), messageDTO.getSenderId(), messageDTO.getReceiverId());
        this.time = new Date().getTime();
        this.message = messageDTO.getContent();
    }

    @Column(name = "time")
    private Long time;

    @Column(name = "message", nullable = false)
    private String message;

}
