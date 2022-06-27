package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {

}
