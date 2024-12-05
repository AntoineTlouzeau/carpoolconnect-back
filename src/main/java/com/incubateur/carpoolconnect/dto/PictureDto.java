package com.incubateur.carpoolconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto {

    private String link;

    private UserDto user;

    private CarDto car;

}
