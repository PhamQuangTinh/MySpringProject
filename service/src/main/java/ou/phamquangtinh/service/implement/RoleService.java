package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.repository.user_repository.RoleJPARepository;
import ou.phamquangtinh.service.component_service.IRoleService;


@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleJPARepository roleRepository;

    @Override
    public RoleEntity findByCode(String code) {
        return roleRepository.findByCode(code);
    }
}
