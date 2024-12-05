package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Car;
import com.incubateur.carpoolconnect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<List<Car>> findByUser(User user);

}
