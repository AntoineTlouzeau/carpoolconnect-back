package com.incubateur.carpoolconnect.utilities.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinatesRequest {

    private int latitude;
    private int longitude;

}
