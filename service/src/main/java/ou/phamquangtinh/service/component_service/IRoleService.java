package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.request.role_request.CreateRoleRequest;
import ou.phamquangtinh.dto.response.role_response.CreateRoleResponse;
import ou.phamquangtinh.dto.response.role_response.GetUserFromRoleResponse;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.entity.UserEntity;

public interface IRoleService {
    GetUserFromRoleResponse findUserByCodeOfRole(String code);

    RoleEntity findRoleByCode(String code);

    RoleEntity createNewRole(CreateRoleRequest request);

    RoleEntity updateRole(RoleEntity roleEntity);

    RoleEntity addNewUser(Long roleId, UserEntity userEntity);


}
