package ou.phamquangtinh.entity;

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
public class ProductAvatarEntity extends BaseEntity<String> {

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
