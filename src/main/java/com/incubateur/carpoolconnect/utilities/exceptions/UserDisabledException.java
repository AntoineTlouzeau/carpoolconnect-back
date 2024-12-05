package com.incubateur.carpoolconnect.utilities.exceptions;

import lombok.Data;

import java.io.Serial;

@Data
public class UserDisabledException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -410378237829438441L;
    private static final String errorMessage = "Ce compte a été désactivé, veuillez contacter un administrateur";
}
