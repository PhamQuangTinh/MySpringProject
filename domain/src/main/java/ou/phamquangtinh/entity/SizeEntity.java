package ou.phamquangtinh.entity;


import lombok.*;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "size")
public class SizeEntity extends BaseEntity<String> {


    @Column(name = "size_type")
    private String sizeType;

    @Column(name = "description",nullable = true, columnDefinition = "TEXT")
    private String description;


    @OneToMany(mappedBy = "sizeEntity",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<AvailableProductsEntity> availableProductsEntities;
}
