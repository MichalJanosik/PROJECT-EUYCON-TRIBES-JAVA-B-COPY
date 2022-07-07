package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KingdomServiceImpl implements KingdomService{

    private final KingdomRepository kingdomRepository;

    @Override
    public Optional<Kingdom> findById(Long id) {
        return kingdomRepository.findById(id);
    }
}
