package com.essence.api.entities.products;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category_types")
public class CategoryType {

    @Getter
    @AllArgsConstructor
    public enum Type {
        CLOTHING(1),
        SHOES(2),
        ACCESSORIES(3);

        private int id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_key", unique = true)
    private Type apiKey;
}
