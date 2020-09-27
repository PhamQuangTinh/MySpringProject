package ou.phamquangtinh.entity.middle_entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ou.phamquangtinh.entity.BaseEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.UserEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@AllArgsConstructor
 @NoArgsConstructor
@Getter
@Setter
@Table(name = "user_comment")
@JsonIgnoreProperties(value = {"userEntity","productCommentDetailEntities"})
public class UserCommentEntity extends BaseEntity<String> {


    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity userEntity;

    @OneToMany(mappedBy = "userCommentEntity", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<ProductCommentDetailEntity> productCommentDetailEntities;
}
