package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.BrandDto;
import com.incubateur.carpoolconnect.entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper( BrandMapper.class );

    BrandDto brandToBrandDto(Brand brand);

    Brand brandDtoToBrand(BrandDto brandDto);

}
