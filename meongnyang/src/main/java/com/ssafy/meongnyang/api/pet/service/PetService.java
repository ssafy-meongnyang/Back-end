package com.ssafy.meongnyang.api.pet.service;

import com.ssafy.meongnyang.api.pet.dto.request.PetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface PetService {
    void registerPet(Long userId, PetRequest petRequest);
}
