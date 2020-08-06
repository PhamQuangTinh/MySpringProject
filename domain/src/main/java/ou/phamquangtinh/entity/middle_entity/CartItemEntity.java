package ou.phamquangtinh.entity.middle_entity;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import ou.phamquangtinh.entity.CartEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CartItemKey;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_item")
public class CartItemEntity {

    @EmbeddedId
    private CartItemKey id;

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


    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne
    @MapsId("cart_id")
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

}
