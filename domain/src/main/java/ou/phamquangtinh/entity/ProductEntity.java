package ou.phamquangtinh.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.entity.middle_entity.ProductCommentDetailEntity;
import ou.phamquangtinh.entity.middle_entity.UserCommentEntity;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Products")
@JsonIgnoreProperties(value = {"orderDetail","productCommentDetailEntities","productColorEntities","categoryEntities","subCategoryEntity"})
public class ProductEntity extends BaseEntity<String> {

    @Column(columnDefinition = "TEXT")
    private String productName;

    @Column
    private double unitPrice;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true)
    private double discount;

    @Column
    private String sexType;

    @OneToMany(mappedBy = "productEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetailEntity> orderDetail;


    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductColorEntity> productColorEntities;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ProductAvatarEntity> productAvatarEntities;

    @ManyToMany(mappedBy = "productEntities")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CategoryEntity> categoryEntities;


    @ManyToMany(mappedBy = "productEntities")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<SubCategoryEntity> subCategoryEntity;

    @ManyToMany(mappedBy = "likeProductEntities")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<UserEntity> productLikeByUserEntities;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ProductCommentDetailEntity> productCommentDetailEntities;
}
