package ou.phamquangtinh.entity.middle_entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ou.phamquangtinh.entity.ColorEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.ProductColorKey;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "product_color")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorEntity {

    @EmbeddedId
    private ProductColorKey id;

    @ManyToOne
    @JoinColumn(name = "product_id",insertable=false, updatable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "color_id",insertable=false, updatable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ColorEntity colorEntity;

    @OneToMany(mappedBy = "productColorEntity")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<AvailableProductEntity> availableProductEntities;

    @OneToMany(mappedBy = "productColorEntity")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<ProductImagesEntity> productImagesEntities;

}
