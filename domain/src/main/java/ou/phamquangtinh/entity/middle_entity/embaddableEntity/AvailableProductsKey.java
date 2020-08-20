package ou.phamquangtinh.entity.middle_entity.embaddableEntity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvailableProductsKey implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "size_id")
    private Long sizeId;

    @Column(name = "color_id")
    private Long colorId;
}
