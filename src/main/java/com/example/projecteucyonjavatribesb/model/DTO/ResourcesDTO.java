package com.example.projecteucyonjavatribesb.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter @Setter
@Builder
@AllArgsConstructor
public class ResourcesDTO {
    private String type;
    private Integer amount;
    private Integer generation;
    //determine what type to use here:
    private Long updatedAt;
}
