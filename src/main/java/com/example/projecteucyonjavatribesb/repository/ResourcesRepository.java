package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    List<Resources> findAllByKingdom_Id(Long id);
}
