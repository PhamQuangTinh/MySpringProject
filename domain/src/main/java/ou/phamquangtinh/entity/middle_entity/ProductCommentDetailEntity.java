package ou.phamquangtinh.entity.middle_entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentDetailKey;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"userCommentEntity","productEntity"})
@Table(name = "comment_detail")
public class ProductCommentDetailEntity {

    @EmbeddedId
    private CommentDetailKey commentDetailKey;

    @Column(columnDefinition = "text")
    private String commentContent;

    @Column
    @CreatedBy
    private String createdBy;

    @Column
    @LastModifiedBy
    private String modifiedBy;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column
    @CreatedDate
    private LocalDateTime createdDate;


    @ManyToOne
    @MapsId("user_comment_id")
    @JoinColumn(name = "user_comment_id")
    private UserCommentEntity userCommentEntity;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
