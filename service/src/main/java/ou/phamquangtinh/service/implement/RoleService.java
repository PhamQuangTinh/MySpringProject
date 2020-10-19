package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ou.phamquangtinh.dto.request.role_request.CreateRoleRequest;
import ou.phamquangtinh.dto.response.role_response.CreateRoleResponse;
import ou.phamquangtinh.dto.response.role_response.GetUserFromRoleResponse;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.repository.RoleJPARepository;
import ou.phamquangtinh.service.component_service.IRoleService;

import java.util.*;
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
        RoleEntity role = findByCode(code);
        return role;
    }

    @Override
    public RoleEntity createNewRole(CreateRoleRequest req) {

        Optional<RoleEntity> roleEntity = roleRepository.findByCode(req.getCode());
        RoleEntity roleRes = new RoleEntity();
        if(!roleEntity.isPresent()){

            roleRes.setCode(req.getCode());
            roleRes.setName(req.getName());
        }
        else{
            return null;
        }

        return roleRepository.saveAndFlush(roleRes);
    }

    @Override
    public RoleEntity updateRole(RoleEntity roleEntity) {
        return roleRepository.saveAndFlush(roleEntity);
    }

    @Override
    public List<RoleEntity> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity addNewUser(Long roleId, UserEntity userEntity) {
        RoleEntity roleEntity = roleRepository.getOne(roleId);
        if(roleEntity.getUsers() == null){
            Collection<UserEntity> userEntities = new HashSet<>();
            userEntities.add(userEntity);
            roleEntity.setUsers(userEntities);
        }else{
            roleEntity.getUsers().add(userEntity);
        }
        return roleRepository.saveAndFlush(roleEntity);
    }

    @Transactional(readOnly = true)
    RoleEntity findByCode(String code) {

        Optional<RoleEntity> role = roleRepository.findByCode(code);

        role.orElseThrow(() -> new RuntimeException("Can't not found role: " + code));
        return role.get();
    }
}
