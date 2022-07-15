package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.Map;
import java.util.Optional;
>>>>>>> EJTB-63-rename-kingdom

@Service

public interface KingdomService {

<<<<<<< HEAD
    public KingdomDetailsDTO getKingdomDetailsDTOById(Long id);
=======
    KingdomDetailsDTO getKingdomDetailsDTOById(Long id);

>>>>>>> EJTB-63-rename-kingdom
    Kingdom findKingdomById(Long id);
    KingdomDTO getKingdomDTO(Long id);
<<<<<<< HEAD
    void renameKingdom(Long kingdomId, KingdomNameDTO kingdomNameDTO);
    KingdomDTO getRenamedKingdomDTO(Long kingdomId);
    List<Kingdom> findAllKingdoms();
=======
    Optional<Kingdom> findById(Long id);

    void renameKingdom(Long kingdomId, KingdomNameDTO kingdomNameDTO);
    KingdomDTO getRenamedKingdomDTO(Long kingdomId);
>>>>>>> EJTB-63-rename-kingdom
}
