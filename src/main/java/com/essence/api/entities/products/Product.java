package com.essence.api.entities.products;

import com.essence.api.entities.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id_seq")
    @SequenceGenerator(name = "products_id_seq", sequenceName = "products_id_seq", allocationSize = 1)
    private Integer id;

    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private Boolean forKids;

    @Column(name = "sex_type_id")
    private Integer sexTypeId;

    @ManyToOne
    @JoinColumn(name = "sex_type_id", insertable = false, updatable = false)
    private SexType sexType;

    @Column(name = "clothing_type_id")
    private Integer clothingTypeId;

    @ManyToOne
    @JoinColumn(name = "clothing_type_id", insertable = false, updatable = false)
    private ClothingType clothingType;

    @Column(name = "category_type_id")
    private Integer categoryTypeId;

    @ManyToOne
    @JoinColumn(name = "category_type_id", insertable = false, updatable = false)
    private CategoryType categoryType;


    @Column(name = "brand_id")
    private Integer brandId;

    @ManyToOne
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
    private Brand brand;

    @ElementCollection
    @Column(name = "image_id")
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    private Set<UUID> imageIds;

    @ManyToMany
    @JoinTable(
            name = "products_sizes",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "size_id", referencedColumnName = "id")
    )
    private Set<SizeType> sizeTypes;

    @ManyToMany
    @JoinTable(
            name = "products_colors",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "color_id", referencedColumnName = "id")
    )
    private Set<ColorType> colorTypes;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;
}
