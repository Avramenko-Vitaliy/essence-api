package com.essence.api.entities.products;

import com.essence.api.utils.TypeCreator;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "color_types")
public class ColorType {

    @Getter
    @AllArgsConstructor
    public enum Type implements TypeCreator<ColorType> {
        BLACK(1),
        WHITE(2),
        RED(3),
        PURPLE(4);

        private final int id;

        @Override
        public ColorType getInstance() {
            return new ColorType(id, this);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_key", unique = true)
    private Type apiKey;
}
