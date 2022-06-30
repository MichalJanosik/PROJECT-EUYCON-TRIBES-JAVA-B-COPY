package com.example.projecteucyonjavatribesb.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KingdomPreviewDTO {

    private String ruler;
    private Long id;
    private String kingdomName;
}
