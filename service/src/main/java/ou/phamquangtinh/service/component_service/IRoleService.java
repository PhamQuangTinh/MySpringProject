package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.dto.response.role_response.GetUserFromRoleResponse;
import ou.phamquangtinh.entity.RoleEntity;

public interface IRoleService {
    GetUserFromRoleResponse findUserByCodeOfRole(String code);

    RoleEntity findRoleByCode(String code);
}
