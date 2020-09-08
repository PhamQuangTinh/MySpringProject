package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.entity.middle_entity.ProductCommentEntity;

public interface IProductCommentService {
    ProductCommentEntity createNewComment(ProductCommentEntity productCommentEntity);

    ListResponsePagination findCommentsProduct(Long proId, int page);
}
