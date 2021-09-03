package com.tie.repository;

import com.tie.model.dao.Message;
import com.tie.model.dao.MessageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatRepository extends JpaRepository<Message, MessageId> {

    @Query(value = "SELECT * " +
            "FROM chats " +
            "WHERE group_id = :groupId " +
            "AND receiver_id = :userId " +
            "AND sender_id = :senderId ",
            nativeQuery = true)
    List<Message> findAllChatsByGroupAndUserId(String groupId, String userId, String senderId);

    @Query(value = "DELETE " +
            "FROM chats " +
            "WHERE group_id = :groupId " +
            "AND receiver_id = :userId " +
            "AND sender_id = :senderId ",
            nativeQuery = true)
    @Modifying
    @Transactional
    void deleteAllChatsByGroupAndUserId(String groupId, String userId, String senderId);

    @Query(value = "DELETE " +
            "FROM chats " +
            "WHERE unique_id = :messageId " +
            "AND receiver_id = :userId ",
            nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByMessageId(String messageId, String userId);
}
