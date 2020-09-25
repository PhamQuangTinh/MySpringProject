package ou.phamquangtinh.entity.middle_entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import ou.phamquangtinh.entity.OrderEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.OderDetailKey;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_detail")
public class OrderDetailEntity {

    @EmbeddedId
    private OderDetailKey id;

    @Column
    private int quantity;

    @Column
    private Long colorId;

    @Column
    private Long sizeId;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;



}
