package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.PictureDto;
import com.incubateur.carpoolconnect.entities.Picture;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PictureMapper {

    PictureMapper INSTANCE = Mappers.getMapper(PictureMapper.class);

    Picture pictureDtoToPicture(PictureDto pictureDto);

    PictureDto pictureToPictureDto(Picture picture);

}
