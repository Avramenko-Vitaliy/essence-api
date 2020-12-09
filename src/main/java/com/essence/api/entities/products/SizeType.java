package com.essence.api.entities.products;

import com.essence.api.utils.TypeCreator;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "size_types")
public class SizeType {

    @Getter
    @AllArgsConstructor
    public enum Type implements TypeCreator<SizeType> {
        XL(1),
        X(2),
        M(3),
        S(4);

        private final int id;

        @Override
        public SizeType getInstance() {
            return new SizeType(id, this);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_key", unique = true)
    private Type apiKey;
}
