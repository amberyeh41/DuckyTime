package com.ironhack.duckytime.services;

import com.ironhack.duckytime.models.SharedSpace;
import com.ironhack.duckytime.repositories.SharedSpaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SharedSpaceService {
    private final SharedSpaceRepository sharedSpaceRepository;

    public SharedSpaceService(SharedSpaceRepository sharedSpaceRepository) {
        this.sharedSpaceRepository = sharedSpaceRepository;
    }

    public SharedSpace saveSharedSpace(SharedSpace sharedSpace) {
        return sharedSpaceRepository.save(sharedSpace);
    }

    public List<SharedSpace> getSharedSpaces(Long adminId) {
        return sharedSpaceRepository.findAllByAdminId(adminId);
    }
}