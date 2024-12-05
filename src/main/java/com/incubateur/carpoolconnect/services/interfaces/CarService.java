package com.incubateur.carpoolconnect.services.interfaces;

import com.incubateur.carpoolconnect.dto.CarDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CarService {

    Object addCar(CarDto car, String authHeader);

    List<CarDto> getFromUser(String authHeader);

    Object throwException();
}
