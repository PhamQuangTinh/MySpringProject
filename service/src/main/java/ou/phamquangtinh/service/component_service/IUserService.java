package ou.phamquangtinh.service.component_service;


import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.dto.request.user_request.UpdateUserReq;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.UserEntity;

public interface IUserService {

    UserEntity createUser(RegisterReq user);

    UserEntity findByUsername(String username);

    UserEntity findUserById(Long id);

    UserEntityResponse findByUserNameResponse(String username);

    UserEntity updateUser(UpdateUserReq userEntity);

    void deleteUserByID(Long id);


}
