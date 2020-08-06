package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ou.phamquangtinh.dto.request.user_request.RegisterReq;
import ou.phamquangtinh.dto.request.user_request.UpdateUserReq;
import ou.phamquangtinh.dto.response.PageMetadata;
import ou.phamquangtinh.dto.response.user_response.ListUsersResponsePagination;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.RoleEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.repository.UserJPARepository;
import ou.phamquangtinh.service.component_service.IRoleService;
import ou.phamquangtinh.service.component_service.IUserService;
import ou.phamquangtinh.service.util.UserServiceUtil;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserServiceUtil userServiceUtil;


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

    //Tìm kiếm User theo tên
    @Override
    public UserEntityResponse findByUserNameResponse(String username) {

        UserEntity userEntity = findByUsername(username);

        UserEntityResponse userRes = userServiceUtil.returnUserEntityResponse(userEntity);

        return userRes;
    }


    @Override
    public UserEntityResponse findUserByIdResponse(Long id) {
        UserEntity userEntity = findUserById(id);
        UserEntityResponse res = userServiceUtil.returnUserEntityResponse(userEntity);
        return  res;
    }


    //Sửa dổi User
    @Override
    public UserEntityResponse updateUser(UpdateUserReq user) {

        UserEntity userEntity = userJPARepository.getOne(user.getId());

        String oldPassword = userEntity.getPassword();

        if (new BCryptPasswordEncoder().matches(user.getOldPass(), oldPassword)) {


            userEntity.setPhone(user.getPhone());

            userEntity.setEmail(user.getEmail());

            userEntity.setFirstName(user.getFirstName());

            userEntity.setLastName(user.getLastName());


            userEntity.setPassword(new BCryptPasswordEncoder().encode(user.getNewPass()));

            userJPARepository.save(userEntity);

            UserEntityResponse userRes = userServiceUtil.returnUserEntityResponse(userEntity);

            return userRes;
        }


        return null;
    }

    //Xóa user theo id
    @Override
    public void deleteUserByID(Long id) {
        UserEntity userEntity = findUserById(id);
        userJPARepository.delete(userEntity);
    }

    @Override
    public List<UserEntityResponse> findByLastName(String lastName) {

        Streamable<UserEntity> user = userJPARepository.findByLastNameContaining(lastName);


        List<UserEntityResponse> res = user.get().map(userServiceUtil::returnUserEntityResponse).collect(Collectors.toList());
        return res;
    }

    @Override
    public List<UserEntityResponse> findUsersByRoleCode(String code) {
        List<UserEntity> userlist = userJPARepository.findByRoles_Code(code);

        return userlist.stream().map(userServiceUtil::returnUserEntityResponse).collect(Collectors.toList());

    }





    @Override
    public ListUsersResponsePagination findByLastNameOrFirstNameContaining(String keyword, int page, int size) {

        Sort sort = Sort.by("lastName").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<UserEntity> users = userJPARepository.findByFirstNameOrLastNameContaining(keyword,keyword, pageable);


        ListUsersResponsePagination res = userServiceUtil.returnListUsersResponsePagination(users);

        return res;
    }

    @Override
    public ListUsersResponsePagination findAllUsers(int page, int size) {
        Sort sort = Sort.by("lastName").descending();
        Pageable pageable = PageRequest.of(page - 1,size,sort);
        Page<UserEntity> userEntityList = userJPARepository.findAll(pageable);

        ListUsersResponsePagination listUsers = userServiceUtil.returnListUsersResponsePagination(userEntityList);

        return listUsers;
    }
}
