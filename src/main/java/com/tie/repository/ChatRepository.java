package com.tie.repository;

import com.tie.model.dao.Chat;
import com.tie.model.dao.ChatId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, ChatId> {

}
