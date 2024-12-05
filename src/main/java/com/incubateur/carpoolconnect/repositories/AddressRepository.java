package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByNumberAndStreetAndCityAndZipcode(int number, String street, String city, int zipcode);

}
