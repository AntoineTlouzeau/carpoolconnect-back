package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.dto.BrandDto;
import com.incubateur.carpoolconnect.mapper.BrandMapper;
import com.incubateur.carpoolconnect.repositories.BrandRepository;
import com.incubateur.carpoolconnect.services.interfaces.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository
                .findAll()
                .stream()
                .map(BrandMapper.INSTANCE::brandToBrandDto)
                .toList();
    }
}
