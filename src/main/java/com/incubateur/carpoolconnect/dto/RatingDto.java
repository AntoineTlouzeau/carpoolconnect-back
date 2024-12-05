package com.incubateur.carpoolconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {

    private int note;

    private UserDto user;

    private RouteDto route;
}
