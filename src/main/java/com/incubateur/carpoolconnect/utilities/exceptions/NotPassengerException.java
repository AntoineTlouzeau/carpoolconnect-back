package com.incubateur.carpoolconnect.utilities.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotPassengerException extends RuntimeException{


    @Serial
    private static final long serialVersionUID = 2472629086066759178L;
    private static final String ERROR_MESSAGE = "L'utilisateur connecté ne correspond pas au passager";
}
