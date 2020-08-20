package ou.phamquangtinh.entity;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;
import ou.phamquangtinh.entity.middle_entity.CartItemEntity;
import ou.phamquangtinh.entity.middle_entity.CommentEntity;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

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
    private String description;

    @Column(nullable = true)
    private double discount;

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


    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SubCategoryEntity subCategoryEntity;

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<AvailableProductsEntity> availableProductsEntities;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ProductAvatarEntity> productAvatarEntities;


}
