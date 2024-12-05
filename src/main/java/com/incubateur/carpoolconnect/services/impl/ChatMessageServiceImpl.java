package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.repositories.ChatMessageRepository;
import com.incubateur.carpoolconnect.services.interfaces.ChatMessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
}
