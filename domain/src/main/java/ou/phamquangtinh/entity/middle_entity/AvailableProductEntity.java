package ou.phamquangtinh.entity.middle_entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ou.phamquangtinh.entity.SizeEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.AvailableProductKey;

import javax.persistence.*;

@Entity
@Table(name = "available_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"productColorEntity","sizeEntity"})
public class AvailableProductEntity {

    @EmbeddedId
    private AvailableProductKey id;

    @Column(nullable = true)
    private int unitInOrder;

    @Column(name = "unit_in_stock")
    private int unitInStock;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id",referencedColumnName = "product_id",insertable = false,updatable = false),
            @JoinColumn(name = "color_id",referencedColumnName = "color_id",insertable = false,updatable = false)
    })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ProductColorEntity productColorEntity;

    @ManyToOne
    @JoinColumn(name = "size_id",insertable = false,updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SizeEntity sizeEntity;

}
