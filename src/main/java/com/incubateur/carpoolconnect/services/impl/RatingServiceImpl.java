package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.repositories.RatingRepository;
import com.incubateur.carpoolconnect.services.interfaces.RatingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
}
