package com.incubateur.carpoolconnect.utilities.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotDriverException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 4721913996176145948L;
    private static final String ERROR_MESSAGE = "L'utilisateur n'est pas le conducteur";

}

