package com.incubateur.carpoolconnect.utilities.exceptions;

import lombok.Data;

import java.util.Set;

@Data
public class ObjectNotValidException extends RuntimeException{

    private final Set<String> errorMessages;

}
