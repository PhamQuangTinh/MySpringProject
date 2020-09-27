package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.middle_entity.ProductCommentDetailEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentDetailKey;
import ou.phamquangtinh.service.implement.CommentDetailService;

import java.util.List;

public interface ICommentDetailService {

    ProductCommentDetailEntity createOrUpdateCommentDetail(ProductCommentDetailEntity commentDetailEntity);

    ProductCommentDetailEntity getCommentDetailToUpdate(CommentDetailKey id);

    List<ProductCommentDetailEntity> findAllCommentsProduct(int page, Long proId);

}
