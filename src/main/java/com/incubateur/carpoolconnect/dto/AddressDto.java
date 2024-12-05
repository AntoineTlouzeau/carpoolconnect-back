package com.incubateur.carpoolconnect.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

//    @NotEmpty
    private int number;

//    @NotNull
//    @NotEmpty
    private String street;

    @NotNull
    @NotEmpty
    private String city;

    @NotEmpty
    private float latitude;

    @NotEmpty
    private float longitude;

    @NotEmpty
    private int zipcode;
}
