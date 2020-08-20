package ou.phamquangtinh.entity.middle_entity;


import lombok.*;
import ou.phamquangtinh.entity.ColorEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.AvailableProductsKey;

import javax.jws.HandlerChain;
import javax.persistence.*;

@Entity
@Table(name = "available_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableProductsEntity {

    @EmbeddedId
    private AvailableProductsKey id;

    @Column(name = "unit_in_stock")
    private int unitInStock;

    @ManyToOne
    @JoinColumn(name = "product_id",insertable=false, updatable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "size_id",insertable=false, updatable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SizeEntity sizeEntity;

    @ManyToOne
    @JoinColumn(name = "color_id",insertable=false, updatable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ColorEntity colorEntity;
}
