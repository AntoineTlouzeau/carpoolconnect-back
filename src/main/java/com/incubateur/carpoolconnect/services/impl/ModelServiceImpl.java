package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.dto.BrandDto;
import com.incubateur.carpoolconnect.dto.ModelDto;
import com.incubateur.carpoolconnect.entities.Brand;
import com.incubateur.carpoolconnect.mapper.ModelMapper;
import com.incubateur.carpoolconnect.repositories.BrandRepository;
import com.incubateur.carpoolconnect.repositories.ModelRepository;
import com.incubateur.carpoolconnect.services.interfaces.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    @Override
    public List<ModelDto> getFromBrand(BrandDto brandDto) {
        Brand brand = brandRepository.findByName(brandDto.getName()).orElseThrow();
        return modelRepository
                .findByBrand(brand)
                .orElseThrow()
                .stream()
                .map(ModelMapper.INSTANCE::modelToModelDto)
                .toList();
    }
}
