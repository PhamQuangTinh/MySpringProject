package ou.phamquangtinh.entity.middle_entity;


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
@Table(name = "order_detail")
public class OrderDetailEntity {

    @EmbeddedId
    private OderDetailKey id;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Column
    private double unitPrice;

    @Column
    private int quantity;

    @Column
    private double discount;

    @Column
    @CreatedBy
    private String createdBy;

    @Column
    @CreatedDate
    private LocalDateTime createdDate;

    @Column
    @LastModifiedBy
    private String modifiedBy;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedDate;


}
