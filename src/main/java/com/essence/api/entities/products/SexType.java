package com.essence.api.entities.products;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sex_types")
public class SexType {

    @Getter
    @AllArgsConstructor
    public enum Type {
        MEN(1),
        WOMEN(2);

        private int id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_key", unique = true)
    private Type apiKey;
}
