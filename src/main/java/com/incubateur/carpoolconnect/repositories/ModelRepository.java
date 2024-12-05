package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Brand;
import com.incubateur.carpoolconnect.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<List<Model>> findByBrand(Brand brand);

}
