package ou.phamquangtinh.service.component_service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.dto.request.user_request.UpdateUserReq;
import ou.phamquangtinh.dto.response.user_response.ListUsersResponsePagination;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.UserEntity;

import java.util.List;

public interface IUserService {

    UserEntity createUser(RegisterReq user);

    UserEntity findByUsername(String username);

    UserEntity findUserById(Long id);

    UserEntityResponse findByUserNameResponse(String username);

    UserEntityResponse updateUser(UpdateUserReq userEntity);

    UserEntityResponse findUserByIdResponse(Long id);

    void deleteUserByID(Long id);

    List<UserEntityResponse> findUsersByRoleCode(String code);

    List<UserEntityResponse> findByLastName(String lastName);

    ListUsersResponsePagination findAllUsers(int page, int size);

    ListUsersResponsePagination findByLastNameOrFirstNameContaining(String keyword, int page, int size);


}
