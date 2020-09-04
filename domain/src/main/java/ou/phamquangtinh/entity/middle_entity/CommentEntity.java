package ou.phamquangtinh.entity.middle_entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentKey;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "comment")
public class CommentEntity {

    @EmbeddedId
    private CommentKey commentKey;

    @Column(columnDefinition = "text")
    private String commentContent;

    @Column
    @CreatedBy
    private String createdBy;

    @Column
    @CreatedDate
    private LocalDateTime createdDate;

    @Column
    @LastModifiedBy
    private String modifiedBy;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductEntity productEntity;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
