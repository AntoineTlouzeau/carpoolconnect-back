package com.incubateur.carpoolconnect.services.interfaces;

import com.incubateur.carpoolconnect.dto.BrandDto;
import com.incubateur.carpoolconnect.dto.ModelDto;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    List<ModelDto> getFromBrand(BrandDto brand);

}
