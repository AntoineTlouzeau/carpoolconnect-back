package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Reservation;
import com.incubateur.carpoolconnect.entities.Route;
import com.incubateur.carpoolconnect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<List<Reservation>> findByUser(User user);
    Optional<List<Reservation>> findByRoute_Driver(User user);

    Optional<List<Reservation>> findByRoute(Route route);
}