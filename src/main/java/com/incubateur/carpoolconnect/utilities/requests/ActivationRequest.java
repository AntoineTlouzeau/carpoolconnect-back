package com.incubateur.carpoolconnect.utilities.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivationRequest {

    @NotNull
    @NotEmpty
    private String key;

    @NotNull
    @NotEmpty
    private String email;
}
