package com.incubateur.carpoolconnect.services.interfaces;

import com.incubateur.carpoolconnect.dto.RouteDto;
import com.incubateur.carpoolconnect.dto.UserDto;
import com.incubateur.carpoolconnect.utilities.requests.AddBioRequest;
import com.incubateur.carpoolconnect.utilities.requests.PasswordChangeRequest;
import com.incubateur.carpoolconnect.utilities.exceptions.IncorrectOldPasswordException;

import java.util.List;

public interface UserService {

    List<RouteDto> getPastRoutes(String authHeader);

    List<RouteDto> getFutureRoutes(String authHeader);

    UserDto changePassword(PasswordChangeRequest request, String authHeader) throws IncorrectOldPasswordException;

    UserDto getFromEmail(String authHeader);

    UserDto addBio(AddBioRequest request, String authHeader);

}
