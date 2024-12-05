package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.ModelDto;
import com.incubateur.carpoolconnect.entities.Model;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {

    ModelMapper INSTANCE = Mappers.getMapper( ModelMapper.class );

    Model modelDtoToModel(ModelDto modelDto);

    ModelDto modelToModelDto(Model model);
}
