package ou.phamquangtinh.entity;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import ou.phamquangtinh.entity.middle_entity.CartItemEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntity<String> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity userEntity;

    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CartItemEntity> cartItemEntities;
}
