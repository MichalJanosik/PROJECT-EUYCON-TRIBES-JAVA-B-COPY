
package com.example.projecteucyonjavatribesb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Integer amount;
    private Integer generation;
    private Long updatedAt;
    @ManyToOne
    @JoinColumn(name = "kingdom")
    private Kingdom kingdom;

    public Resources(String type, Integer amount, Integer generation, Long updatedAt) {
        this.type = type;
        this.amount = amount;
        this.generation = generation;
        this.updatedAt = updatedAt;
    }
}

package com.example.projecteucyonjavatribesb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Integer amount;
    private Integer generation;
    private Long updatedAt;
    @ManyToOne
    @JoinColumn(name = "kingdom")
    private Kingdom kingdom;

    public Resources(String type, Integer amount, Integer generation, Long updatedAt) {
        this.type = type;
        this.amount = amount;
        this.generation = generation;
        this.updatedAt = updatedAt;
    }
}

