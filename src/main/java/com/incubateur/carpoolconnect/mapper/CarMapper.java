package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.CarDto;
import com.incubateur.carpoolconnect.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    @Mapping(ignore = true, target = "routes")
    @Mapping(ignore = true, target = "pictures")
    @Mapping(ignore = true, target = "user")
    Car carDtotoCar(CarDto carDto);

    CarDto carToCarDto(Car car);
}
