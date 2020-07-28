package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.dto.request.user_request.UpdateUserReq;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.repository.UserJPARepository;
import ou.phamquangtinh.service.component_service.IRoleService;
import ou.phamquangtinh.service.component_service.IUserService;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private IRoleService roleService;


    @Override
    public UserEntity createUser(RegisterReq user) {
        Optional<UserEntity> userEntity = userJPARepository.findByUsername(user.getUsername());
        if (!userEntity.isPresent()) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            Collection<RoleEntity> roles = user.getRoles()
                    .stream()
                    .map(code -> roleService.findRoleByCode(code))
                    .collect(Collectors.toList());
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userResponse = modelMapper.map(user, UserEntity.class);
            userResponse.setRoles(roles);
            return userJPARepository.saveAndFlush(userResponse);
        }
        return null;
    }

    @Override
    public UserEntity findByUsername(String username) {
        Optional<UserEntity> user = userJPARepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found user: " + username));

        return user.get();
    }

    @Override
    public UserEntity findUserById(Long id) {
        Optional<UserEntity> user = userJPARepository.findById(id);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found user id: " + id));
        return user.get();
    }

    @Override
    public UserEntityResponse findByUserNameResponse(String username) {
        UserEntity userEntity = findByUsername(username);
        List<String> rolesResponse = userEntity.getRoles()
                .stream()
                .map(roleEntity -> roleEntity.getCode())
                .collect(Collectors.toList());

        UserEntityResponse userRes = new UserEntityResponse();
        userRes = mapUserEntityToObject(userEntity, userRes);
        userRes.setRolesResponse(rolesResponse);
        return userRes;
    }


    @Override
    public UserEntity updateUser(UpdateUserReq user) {

        UserEntity userEntity = findByUsername(user.getUsername());

        String oldPassword = userEntity.getPassword();

        if (new BCryptPasswordEncoder().matches(user.getOldPass(), oldPassword)) {

            String newPass = new BCryptPasswordEncoder().encode(user.getNewPass());

            ModelMapper modelMapper = new ModelMapper();

            UserEntity userEntityRes = modelMapper.map(user, UserEntity.class);

            userEntityRes.setPassword(newPass);

            return userJPARepository.save(userEntityRes);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUserByID(Long id) {
        UserEntity userEntity = findUserById(id);
        userJPARepository.delete(userEntity);
    }

    private UserEntity mapObjectToUserEntity(Object objectMapping) {
        ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = mapper.map(objectMapping, UserEntity.class);
        return userEntity;
    }

    private <T extends Object> T mapUserEntityToObject(UserEntity userEntity, T ok) {
        ModelMapper mapper = new ModelMapper();
        T objectMapping = mapper.map(userEntity, (Type) ok.getClass());
        return objectMapping;
    }


}
