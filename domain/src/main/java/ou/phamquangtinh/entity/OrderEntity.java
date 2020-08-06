package ou.phamquangtinh.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;

import javax.persistence.*;
import java.util.Collection;


@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity<String> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ShipperEntity shipperEntity;

    @OneToMany(mappedBy = "orderEntity")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<OrderDetailEntity> orderDetail;


}
