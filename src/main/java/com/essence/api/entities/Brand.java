package com.essence.api.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brands_id_seq")
    @SequenceGenerator(name = "brands_id_seq", sequenceName = "brands_id_seq", allocationSize = 1)
    private Integer id;

    private String name;
    private UUID logoId;
}
