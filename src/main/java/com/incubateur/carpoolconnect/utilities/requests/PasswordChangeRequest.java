package com.incubateur.carpoolconnect.utilities.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordChangeRequest {

    @NotNull
    @NotEmpty
    String oldPassword;

    @NotNull
    @NotEmpty
    String newPassword;

}
