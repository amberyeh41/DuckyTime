package com.ironhack.duckytime.services;

import com.ironhack.duckytime.repositories.SharedSpaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SharedSpaceService {
    private final SharedSpaceRepository sharedSpaceRepository;

    public SharedSpaceService(SharedSpaceRepository sharedSpaceRepository) {
        this.sharedSpaceRepository = sharedSpaceRepository;
    }
}