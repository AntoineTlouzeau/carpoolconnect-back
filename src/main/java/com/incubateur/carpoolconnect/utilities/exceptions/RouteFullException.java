package com.incubateur.carpoolconnect.utilities.exceptions;

import lombok.Data;

import java.io.Serial;

@Data
public class RouteFullException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 7529718125333614200L;
    private static final String ERROR_MESSAGE = "Ce trajet n'as plus de place disponible";


}
