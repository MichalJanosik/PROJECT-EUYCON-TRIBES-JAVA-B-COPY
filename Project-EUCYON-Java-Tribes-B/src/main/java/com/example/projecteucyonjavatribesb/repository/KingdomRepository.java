package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom,Long> {
    @Override
    Optional<Kingdom> findById(Long id);
}
