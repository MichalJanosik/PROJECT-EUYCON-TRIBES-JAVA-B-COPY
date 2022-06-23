package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.RequestDTO;
import org.springframework.http.ResponseEntity;

public interface LocationService {
    ResponseEntity<String> createLocation(RequestDTO requestDTO);
}
