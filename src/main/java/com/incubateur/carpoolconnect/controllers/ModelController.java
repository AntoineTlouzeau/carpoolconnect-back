package com.incubateur.carpoolconnect.controllers;

import com.incubateur.carpoolconnect.dto.BrandDto;
import com.incubateur.carpoolconnect.dto.ModelDto;
import com.incubateur.carpoolconnect.services.interfaces.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/model")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @GetMapping(value = "/brand")
    public ResponseEntity<List<ModelDto>> getFromBrand(@RequestBody BrandDto brand) {
        return ResponseEntity.ok(modelService.getFromBrand(brand));
    }
}
