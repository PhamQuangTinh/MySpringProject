package ou.phamquangtinh.entity.middle_entity.embaddableEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentKey implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "user_id")
    private Long userId;
    
}
