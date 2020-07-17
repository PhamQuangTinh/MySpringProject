package ou.phamquangtinh.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.repository.user_repository.UserRepository;
import ou.phamquangtinh.service.component_service.IRoleService;
import ou.phamquangtinh.service.component_service.IUserService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IRoleService roleService;

//    @Override
//    public UserEntity createUser(UserEntity user) {
//        return userRepository.save(user);
//    }

    @Override
    public UserEntity createUser(RegisterReq user) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(user.getUsername());
        if (!userEntity.isPresent()) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            Collection<RoleEntity> roles = user.getRoles()
                    .stream()
                    .map(code -> roleService.findByCode(code))
                    .collect(Collectors.toList());
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userResponse = modelMapper.map(user, UserEntity.class);
            userResponse.setRoles(roles);
            return userRepository.saveAndFlush(userResponse);
        }

        return null;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found " + username));
        return user;
    }

}
