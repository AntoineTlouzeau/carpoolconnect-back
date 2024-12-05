package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.AddressDto;
import com.incubateur.carpoolconnect.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(ignore = true, target = "routeDepart")
    @Mapping(ignore = true, target = "routeDestination")
    @Mapping(ignore = true, target = "routes")
    Address addressDtoToAddress(AddressDto addressDto);


    AddressDto addressToAddressDto(Address address);
}
