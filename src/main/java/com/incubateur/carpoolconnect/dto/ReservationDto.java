package com.incubateur.carpoolconnect.dto;

import com.incubateur.carpoolconnect.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private long id;

    private UserDto user;

    private RouteDto route;

    private int numberOfPassengers;

    private ReservationStatus status;
}
