package ou.phamquangtinh.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ou.phamquangtinh.entity.middle_entity.OrderDetailEntity;

import javax.persistence.*;
import java.util.Collection;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
@JsonIgnoreProperties(value = {"orderDetail"})
public class OrderEntity extends BaseEntity<String> {

    @Column
    private String payerId;

    @Column
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity userEntity;


    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<OrderDetailEntity> orderDetail;


}
