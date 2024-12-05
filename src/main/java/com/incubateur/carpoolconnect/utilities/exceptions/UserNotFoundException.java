package com.incubateur.carpoolconnect.utilities.exceptions;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{

    private final String message = "Cet adresse email ne correspond à aucun utilisateur";
}
