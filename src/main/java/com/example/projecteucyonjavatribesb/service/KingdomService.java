package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
<<<<<<< HEAD
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
=======
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
>>>>>>> b4f47ed03aaaf6328bdc8cc9b3575b23bb4a2da9
import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.List;

=======
import java.util.Map;

@Service
>>>>>>> b4f47ed03aaaf6328bdc8cc9b3575b23bb4a2da9
public interface KingdomService {

    public KingdomDetailsDTO getKingdomDetailsDTOById(Long id);

    Kingdom findKingdomById(Long id);

    KingdomDTO getKingdomDTO(Long id);
<<<<<<< HEAD
    void renameKingdom(Long kingdomId, KingdomNameDTO kingdomNameDTO);
    KingdomDTO getRenamedKingdomDTO(Long kingdomId);
    List<Kingdom> findAllKingdoms();
=======

>>>>>>> b4f47ed03aaaf6328bdc8cc9b3575b23bb4a2da9
}
