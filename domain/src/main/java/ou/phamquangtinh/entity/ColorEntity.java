package ou.phamquangtinh.entity;


import lombok.*;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "color")
public class ColorEntity extends BaseEntity<String> {

    @Column(name = "color_name")
    private String colorName;

    @Column(name="color_link")
    private String colorLink;

    @Column(name = "description",nullable = true, columnDefinition = "TEXT")
    private String description;



    @OneToMany(mappedBy = "productColorEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductImagesEntity> productImagesEntities;

    @OneToMany(mappedBy = "colorEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<AvailableProductsEntity> availableProductsEntities;

}
