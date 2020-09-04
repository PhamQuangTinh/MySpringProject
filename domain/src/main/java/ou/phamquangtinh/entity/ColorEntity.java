package ou.phamquangtinh.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;

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
@JsonIgnoreProperties(value = {"productColorEntities"})
public class ColorEntity extends BaseEntity<String> {

    @Column(name = "color_name")
    private String colorName;

    @Column(name="color_link")
    private String colorLink;

    @Column(name = "description",nullable = true, columnDefinition = "TEXT")
    private String description;


    @OneToMany(mappedBy = "colorEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductColorEntity> productColorEntities;

}
