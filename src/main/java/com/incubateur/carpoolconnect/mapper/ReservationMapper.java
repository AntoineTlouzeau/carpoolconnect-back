package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.ReservationDto;
import com.incubateur.carpoolconnect.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    Reservation reservationDtoToReservation(ReservationDto reservationDto);

    ReservationDto reservationToReservationDto(Reservation reservation);

}
