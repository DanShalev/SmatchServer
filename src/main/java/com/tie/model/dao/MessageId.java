package com.tie.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@Data
public class MessageId implements Serializable {

    public MessageId(String groupId, String senderId, String receiverId) {
        this.groupId = groupId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.uniqueId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    @Column(name = "group_id", nullable = false)
    private String groupId;

    @Column(name = "sender_id", nullable = false)
    private String senderId;

    @Column(name = "receiver_id", nullable = false)
    private String receiverId;

    @Column(name = "unique_id", nullable = false)
    private long uniqueId;

}
