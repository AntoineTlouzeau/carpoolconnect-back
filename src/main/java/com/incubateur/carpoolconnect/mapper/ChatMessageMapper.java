package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.ChatMessageDto;
import com.incubateur.carpoolconnect.entities.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMessageMapper {

    ChatMessageMapper INSTANCE = Mappers.getMapper( ChatMessageMapper.class );

    ChatMessage chatMessageDtoToChatMessage(ChatMessageDto chatMessageDto);

    ChatMessageDto chatMessageToChatMessageDto(ChatMessage chatMessage);
}
