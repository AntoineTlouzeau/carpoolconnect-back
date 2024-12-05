package com.incubateur.carpoolconnect.dto;

import com.incubateur.carpoolconnect.enums.Colors;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {

    @NotNull(message = "The model should not be empty")
    @NotEmpty(message = "The model should not be empty")
    private ModelDto model;

    @NotNull(message = "The color should not be empty")
    @NotEmpty(message = "The color should not be empty")
    private Colors color;

    private List<PictureDto> pictures;
}
