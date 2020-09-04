package ou.phamquangtinh.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "product_images")
@JsonIgnoreProperties(value = {"productColorEntity"})

public class ProductImagesEntity extends BaseEntity<String>{

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id",referencedColumnName = "product_id"),
            @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    })
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private ProductColorEntity productColorEntity;
}
