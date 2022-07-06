package com.example.projecteucyonjavatribesb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Buildings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private int level;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    // in the main() method
    LocalDateTime now = LocalDateTime.now();
    // Adding 1 year, 1 month, 1 week and 1 day
    LocalDateTime localDateTime1 = now.plusYears(1)
            .plusMonths(1)
            .plusWeeks(1)
            .plusDays(1);
    @ManyToOne
    @JoinColumn(name = "kingdom")
    private Kingdom kingdom;


}
