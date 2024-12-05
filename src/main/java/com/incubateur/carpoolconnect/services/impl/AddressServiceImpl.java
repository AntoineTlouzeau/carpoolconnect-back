package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.dto.AddressDto;
import com.incubateur.carpoolconnect.mapper.AddressMapper;
import com.incubateur.carpoolconnect.repositories.AddressRepository;
import com.incubateur.carpoolconnect.services.interfaces.AddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public AddressDto addAddress(AddressDto addressDto) {
        addressRepository.saveAndFlush(AddressMapper.INSTANCE.addressDtoToAddress(addressDto));
        return addressDto;
    }
}
