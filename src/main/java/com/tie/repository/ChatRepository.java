package com.tie.repository;

import com.tie.model.dao.Chat;
import com.tie.model.dao.ChatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, ChatId> {

    @Query(value = "SELECT * " +
            "FROM chats " +
            "WHERE group_id = :groupId " +
            "AND receiver_id = :userId " +
            "AND sender_id = :senderId ",
            nativeQuery = true)
    List<Chat> findAllChatsByGroupAndUserId(String groupId, String userId, String senderId);

}
