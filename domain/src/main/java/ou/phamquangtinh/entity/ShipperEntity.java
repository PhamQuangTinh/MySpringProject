package ou.phamquangtinh.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "shippers")
@Getter
@Setter
@AllArgsConstructor
public class ShipperEntity extends BaseEntity<String> {


    @OneToMany(mappedBy = "shipperEntity")
    private Set<OrderEntity> orders;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @Column
    private String email;


}
