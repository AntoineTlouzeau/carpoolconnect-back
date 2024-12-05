package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.repositories.RoleRepository;
import com.incubateur.carpoolconnect.services.interfaces.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
}
