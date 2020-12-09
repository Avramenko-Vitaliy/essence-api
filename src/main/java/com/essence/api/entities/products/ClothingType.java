package com.essence.api.entities.products;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clothing_types")
public class ClothingType {

    @Getter
    @AllArgsConstructor
    public enum Type {
        DRESSES(1),
        BLOUSES(2),
        SHIRTS(3),
        T_SHIRT(4),
        ROMPERS(5),
        BRAS(6),
        PANTIES(7),
        POLO(8),
        JACKETS(9),
        TRENCH(10);

        private int id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_key", unique = true)
    private Type apiKey;
}
