package com.incubateur.carpoolconnect.utilities.exceptions;

import java.io.Serial;

public class IncorrectOldPasswordException extends Exception{

    @Serial
    private static final long serialVersionUID = -6128142233723830256L;

    public IncorrectOldPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
