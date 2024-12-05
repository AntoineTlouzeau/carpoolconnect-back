package com.incubateur.carpoolconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteDto {

    private long id;
    private LocalDateTime departureDate;
    private int seats;
    private int smallBaggage;
    private int largeBaggage;
    private boolean silence;
    private String musicGenre;
    private UserDto driver;
    private CarDto car;
    private AddressDto departure;
    private AddressDto destination;
    private List<AddressDto> intermediateAddresses;
    private List<RatingDto> ratings;
}
