package ou.phamquangtinh.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_avatar")
@JsonIgnoreProperties(value = {"productEntity"})
public class ProductAvatarEntity extends BaseEntity<String> {

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductEntity productEntity;
}
