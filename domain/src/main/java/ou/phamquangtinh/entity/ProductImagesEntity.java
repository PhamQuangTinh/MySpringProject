package ou.phamquangtinh.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "product_images")
public class ProductImagesEntity extends BaseEntity<String>{

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "color_id")
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private ColorEntity productColorEntity;
}
