package ou.phamquangtinh.entity;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import ou.phamquangtinh.entity.middle_entity.CartItemEntity;
import ou.phamquangtinh.entity.middle_entity.CommentEntity;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Products")
public class ProductEntity extends BaseEntity<String> {

    @Column
    private String productName;

    @Column
    private double unitPrice;

    @Column(nullable = true)
    private int unitInOrder;

    @Column(nullable = true)
    private int unitInStock;

    @Column(nullable = true)
    private String Description;

    @Column(nullable = true)
    private double Discount;

    @OneToMany(mappedBy = "productEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetailEntity> orderDetail;

    @OneToMany(mappedBy = "productEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CommentEntity> comments;

    @OneToMany(mappedBy = "productEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CartItemEntity> cartItemEntities;

    @ManyToMany
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Collection<CategoryEntity> categorys;


}
