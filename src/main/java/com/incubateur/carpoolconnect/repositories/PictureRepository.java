package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
