package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByCoordinateXAndAndCoordinateY(Integer x, Integer y);
}
