package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.repositories.CommentRepository;
import com.incubateur.carpoolconnect.services.interfaces.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
}
