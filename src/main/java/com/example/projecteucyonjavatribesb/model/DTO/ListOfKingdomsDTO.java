package com.example.projecteucyonjavatribesb.model.DTO;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListOfKingdomsDTO {

    private List<KingdomDTO> kingdoms;


}
