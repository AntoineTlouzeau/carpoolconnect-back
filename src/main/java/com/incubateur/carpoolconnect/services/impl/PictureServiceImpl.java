package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.repositories.PictureRepository;
import com.incubateur.carpoolconnect.services.interfaces.PictureService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
}
