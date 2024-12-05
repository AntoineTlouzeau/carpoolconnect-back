package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.RatingDto;
import com.incubateur.carpoolconnect.entities.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RatingMapper {

    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    Rating ratingDtoToRating(RatingDto ratingDto);

    RatingDto ratingToRatingDto(Rating rating);
}
