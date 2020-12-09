package com.essence.api.entities.security;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissions")
public class Permission {

    @Getter
    @AllArgsConstructor
    public enum Type {
        MANAGE_PRODUCTS(1),
        VIEW_PRODUCTS(2);

        private int id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_key", unique = true)
    private Type apiKey;
}
