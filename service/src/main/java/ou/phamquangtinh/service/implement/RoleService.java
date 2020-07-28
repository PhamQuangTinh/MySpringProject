package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ou.phamquangtinh.dto.response.role_response.GetUserFromRoleResponse;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.repository.RoleJPARepository;
import ou.phamquangtinh.service.component_service.IRoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class RoleService implements IRoleService {

    @Autowired
    private RoleJPARepository roleRepository;

    @Override
    public GetUserFromRoleResponse findUserByCodeOfRole(String code) {
        RoleEntity roleEntity = findRoleByCode(code);

        GetUserFromRoleResponse s = new GetUserFromRoleResponse();
        ;
        List<UserEntity> userEntityList = new ArrayList<>(roleEntity.getUsers());


        GetUserFromRoleResponse res =
                new GetUserFromRoleResponse(roleEntity.getId(), roleEntity.getCode(),
                        roleEntity.getName(), userEntityList.stream()
                        .map(x -> new GetUserFromRoleResponse.user(x.getId(), x.getUsername(),
                                x.getFirstName(), x.getPhone(), x.getEmail())).collect(Collectors.toList()));

        return res;
    }

    @Override
    public RoleEntity findRoleByCode(String code) {
        Optional<RoleEntity> role = findByCode(code);

        role.orElseThrow(() -> new RuntimeException("Can't not found role: " + code));
        return role.get();
    }

    @Transactional(readOnly = true)
    private Optional<RoleEntity> findByCode(String code) {
        return roleRepository.findByCode(code);
    }
}
