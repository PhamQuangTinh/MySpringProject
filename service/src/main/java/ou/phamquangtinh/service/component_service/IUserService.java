package ou.phamquangtinh.service.component_service;


import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.dto.request.user_request.UpdateUserReq;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.OrderEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.entity.middle_entity.UserCommentEntity;

import java.util.List;

public interface IUserService {

    UserEntity createUser(RegisterReq user);

    UserEntity updateUser(UserEntity userEntity);

    UserEntity findByUsername(String username);

    UserEntity findUserById(Long id);

    UserEntity findByUserNameResponse(String username);

    UserEntity updateUser(UpdateUserReq userEntity);

    UserEntity findUserByIdResponse(Long id);

    UserEntity getUserToUpdate(Long id);

    void deleteUserByID(Long id);

    ListResponsePagination findUsersByRoleCodePagination(String code, int page, int size);

    List<UserEntity> findByLastName(String lastName);

    ListResponsePagination findAllUsers(int page, int size);

    ListResponsePagination findByLastNameOrFirstNameContaining(String keyword, int page, int size);

    void likeProduct(Long userId, Long productId);

    void unLikeProduct(Long userId, Long proId);

    void addNewUserComment(Long userId, UserCommentEntity userCommentEntity);

    UserEntity addNewComment(Long userId, Long productId, String content);

    UserEntity addNewOrder(Long userId, OrderEntity orderEntity);
}
