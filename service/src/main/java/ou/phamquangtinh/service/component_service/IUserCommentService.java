package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.middle_entity.ProductCommentDetailEntity;
import ou.phamquangtinh.entity.middle_entity.UserCommentEntity;

public interface IUserCommentService {

    UserCommentEntity addNewUserToComment(Long userId);

    UserCommentEntity addNewCommentDetail(Long userCommentId, ProductCommentDetailEntity productCommentDetailEntity);

    UserCommentEntity findUserCommentById(Long id);

    UserCommentEntity getUserToUpdate(Long id);

    UserCommentEntity createOrUpdateUserComment(UserCommentEntity userCommentEntity);

    UserCommentEntity createNewCommentToProduct(Long userId, Long proId, String commentContent);
}
