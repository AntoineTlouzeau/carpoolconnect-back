package com.incubateur.carpoolconnect.utilities.requests;

import com.incubateur.carpoolconnect.dto.AddressDto;
import com.incubateur.carpoolconnect.dto.CarDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddRouteRequest {

    private LocalDateTime departureDate;
    private int seats;
    private int smallBaggage;
    private int largeBaggage;
    private boolean silence;
    private String musicGenre;
    private String driverEmail;
    private AddressDto departure;
    private AddressDto destination;
    private CarDto car;
}
