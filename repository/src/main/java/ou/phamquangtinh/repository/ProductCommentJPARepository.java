package ou.phamquangtinh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.middle_entity.ProductCommentEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentKey;

import java.util.Optional;

public interface ProductCommentJPARepository extends JpaRepository<ProductCommentEntity, CommentKey> {

    Page<ProductCommentEntity> findByCommentKey_ProductId(Long proId, Pageable page);

    Optional<ProductCommentEntity> findByCommentKey(CommentKey commentKey);
}
