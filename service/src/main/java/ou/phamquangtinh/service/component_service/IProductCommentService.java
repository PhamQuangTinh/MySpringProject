package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.entity.middle_entity.ProductCommentEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentKey;

public interface IProductCommentService {
    ProductCommentEntity createNewComment(ProductCommentEntity productCommentEntity);

    ProductCommentEntity findProductByCommentKey(CommentKey commentKey);

    ListResponsePagination findCommentsProduct(Long proId, int page);
}
