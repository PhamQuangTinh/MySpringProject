package ou.phamquangtinh.service.component_service;


import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.entity.UserEntity;

import java.util.Optional;

public interface IUserService {

    UserEntity createUser(RegisterReq user);

    Optional<UserEntity> findByUsername(String username);

}
