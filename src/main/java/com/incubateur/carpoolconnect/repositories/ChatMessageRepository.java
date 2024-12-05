package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
