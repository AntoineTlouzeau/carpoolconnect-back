package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.RouteDto;
import com.incubateur.carpoolconnect.entities.Route;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    Route routeDtoToRoute(RouteDto routeDto);

    RouteDto routeToRouteDto(Route route);
}
