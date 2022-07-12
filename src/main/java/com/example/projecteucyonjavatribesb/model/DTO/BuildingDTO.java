package com.example.projecteucyonjavatribesb.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BuildingDTO {
    private long id;
    private String type;
    private int level;
    private Long startedAt;
    private Long finishedAt;

}
