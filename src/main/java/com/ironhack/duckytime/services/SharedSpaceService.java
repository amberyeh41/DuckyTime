package com.ironhack.duckytime.services;

import com.ironhack.duckytime.exceptions.ResourceNotFoundException;
import com.ironhack.duckytime.models.Admin;
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

    public void saveSharedSpace(SharedSpace sharedSpace) {
        sharedSpaceRepository.save(sharedSpace);
    }

    public SharedSpace renameSharedSpace(SharedSpace sharedSpace, String newName) {
        sharedSpace.setName(newName);
        sharedSpaceRepository.save(sharedSpace);
        return sharedSpace;
    }

    public List<SharedSpace> getSharedSpaces(Long adminId) {
        return sharedSpaceRepository.findAllByAdminId(adminId);
    }

    public SharedSpace getSharedSpace(Long adminId, Long id) {
        SharedSpace space = sharedSpaceRepository.findByAdminIdAndId(adminId, id);
        if (space == null) {
            throw new ResourceNotFoundException("Shared space not found");
        }
        return space;
    }

    public void deleteSharedSpace(Long adminId, Long id) {
        SharedSpace space = sharedSpaceRepository.findByAdminIdAndId(adminId, id);
        if (space == null) {
            throw new ResourceNotFoundException("Shared space not found");
        }
        sharedSpaceRepository.deleteById(id.intValue());
    }
}