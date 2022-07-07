package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;

import java.util.Optional;

public interface KingdomService {
    Optional<Kingdom> findById(Long id);
}
