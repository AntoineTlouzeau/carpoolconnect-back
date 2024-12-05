package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Route;
import com.incubateur.carpoolconnect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<List<Route>> findByDriver(User user);
    Optional<List<Route>> findByDeparture_Latitude(int latitude);
    Optional<List<Route>> findByDeparture_Longitude(int longitude);
    Optional<List<Route>> findByDestination_Latitude(int latitude);
    Optional<List<Route>> findByDestination_Longitude(int longitude);
    Optional<List<Route>> findByDeparture_City(String city);
    Optional<List<Route>> findByDestination_City(String city);
//    @Query("SELECT r FROM Route r WHERE r.departure.city = :departureCity " +
//            "AND r.destination.city = :destinationCity " +
//            "AND r.departureDate BETWEEN :startInterval AND :endInterval " +
//            "AND r.seats >= :seats " +
//            "AND r.largeBaggage <= :baggage")
//    Optional<List<Route>> findByDeparture_CityAndDestination_CityAndDepartureDateBetween(String departureCity, String destinationCity, LocalDateTime startInterval, LocalDateTime endInterval, int seats, int baggage);
    @Query("SELECT r FROM Route r WHERE " +
            "ST_Distance_Sphere(point(r.departure.longitude, r.departure.latitude), point(:departLong, :departLat)) <= :departRadius " +
            "AND ST_Distance_Sphere(point(r.destination.longitude, r.destination.latitude), point(:destinationLong, :destinationLat)) <= :destinationRadius " +
            "AND r.departureDate BETWEEN :startInterval AND :endInterval " +
            "AND r.seats >= :seats " +
            "AND r.largeBaggage >= :baggage ")
    Optional<List<Route>> findByLocationAndDateWithinRadius(
                                                  float departLat,
                                                  float departLong,
                                                  float destinationLat,
                                                  float destinationLong,
                                                  int departRadius,
                                                  int destinationRadius,
                                                  LocalDateTime startInterval,
                                                  LocalDateTime endInterval,
                                                  int seats,
                                                  int baggage);

}
