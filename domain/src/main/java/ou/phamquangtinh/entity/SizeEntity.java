package ou.phamquangtinh.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "size")
@JsonIgnoreProperties(value = {"availableProductsEntities"})
public class SizeEntity extends BaseEntity<String> {


    @Column(name = "size_type")
    private String sizeType;

    @Column(name = "description",nullable = true, columnDefinition = "TEXT")
    private String description;


    @OneToMany(mappedBy = "sizeEntity",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<AvailableProductEntity> availableProductsEntities;
}
