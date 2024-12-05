package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.UserDto;
import com.incubateur.carpoolconnect.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(ignore = true, target = "messagesSent")
    @Mapping(ignore = true, target = "messagesReceived")
    @Mapping(ignore = true, target = "comments")
    @Mapping(ignore = true, target = "routesProposed")
    @Mapping(ignore = true, target = "ratings")
    @Mapping(ignore = true, target = "cars")
    @Mapping(ignore = true, target = "icons")
    @Mapping(ignore = true, target = "reservations")
    @Mapping(ignore = true, target = "role")
    User userDtoToUser(UserDto userDto);


    UserDto userToUserDto(User user);
}
