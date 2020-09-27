package ou.phamquangtinh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ou.phamquangtinh.entity.middle_entity.ProductCommentDetailEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentDetailKey;

public interface CommentDetailJPARepository extends JpaRepository<ProductCommentDetailEntity, CommentDetailKey> {

    Page<ProductCommentDetailEntity> findByCommentDetailKey_ProductId(Long proId, Pageable pageable);
}
