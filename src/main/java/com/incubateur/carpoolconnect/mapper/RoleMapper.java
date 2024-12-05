package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.RoleDto;
import com.incubateur.carpoolconnect.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role roleDtoToRole(RoleDto roleDto);

    RoleDto roleToRoleDto(Role role);
}
