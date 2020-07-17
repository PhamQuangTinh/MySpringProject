package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.RoleEntity;

public interface IRoleService {
    RoleEntity findByCode(String code);
}
