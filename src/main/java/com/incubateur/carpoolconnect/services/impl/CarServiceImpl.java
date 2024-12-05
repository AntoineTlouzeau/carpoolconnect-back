package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.dto.CarDto;
import com.incubateur.carpoolconnect.entities.Car;
import com.incubateur.carpoolconnect.entities.User;
import com.incubateur.carpoolconnect.mapper.CarMapper;
import com.incubateur.carpoolconnect.repositories.CarRepository;
import com.incubateur.carpoolconnect.repositories.UserRepository;
import com.incubateur.carpoolconnect.services.interfaces.CarService;
import com.incubateur.carpoolconnect.utilities.ObjectsValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ObjectsValidator<CarDto> validator;

    @Override
    public Object addCar(CarDto carDto, String authHeader) {
        validator.validate(carDto);

        Car car = CarMapper.INSTANCE.carDtotoCar(carDto);
        car.setUser(userRepository.findByEmail(jwtService.extractUsername(authHeader.substring(7))).orElseThrow());
        carRepository.save(car);
        return CarMapper.INSTANCE.carToCarDto(car);
    }

    @Override
    public List<CarDto> getFromUser(String authHeader) {
        final String token = authHeader.substring(7);
        final User user = userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow();
        return carRepository.findByUser(user)
                .orElseThrow()
                .stream()
                .map(CarMapper.INSTANCE::carToCarDto)
                .toList();
    }

    @Override
    public String throwException() {
        throw new IllegalStateException("Some exception happened");
    }

}
