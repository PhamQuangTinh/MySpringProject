package ou.phamquangtinh.service.component_service;


import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.dto.request.user_request.UpdateUserReq;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.UserEntity;

import java.util.List;

public interface IUserService {

    UserEntity createUser(RegisterReq user);

    UserEntity findByUsername(String username);

    UserEntity findUserById(Long id);

    UserEntity findByUserNameResponse(String username);

    UserEntity updateUser(UpdateUserReq userEntity);

    UserEntity findUserByIdResponse(Long id);

    void deleteUserByID(Long id);

    ListResponsePagination findUsersByRoleCodePagination(String code, int page, int size);

    List<UserEntity> findByLastName(String lastName);

    ListResponsePagination findAllUsers(int page, int size);

    ListResponsePagination findByLastNameOrFirstNameContaining(String keyword, int page, int size);


}
