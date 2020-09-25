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
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.dto.response.user_response.UserEntityResponse;
import ou.phamquangtinh.entity.*;
import ou.phamquangtinh.entity.middle_entity.ProductCommentEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentKey;
import ou.phamquangtinh.repository.UserJPARepository;
import ou.phamquangtinh.service.component_service.IProductCommentService;
import ou.phamquangtinh.service.component_service.IProductService;
import ou.phamquangtinh.service.component_service.IRoleService;
import ou.phamquangtinh.service.component_service.IUserService;
import ou.phamquangtinh.service.util.CommonUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductCommentService productCommentService;

    @Override
    public UserEntity createUser(RegisterReq user) {
        Optional<UserEntity> userEntity = userJPARepository.findByUsername(user.getUsername());
        if (!userEntity.isPresent()) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userResponse = modelMapper.map(user, UserEntity.class);
            UserEntity userEntityRes =  userJPARepository.saveAndFlush(userResponse);
            userEntityRes = userJPARepository.getOne(userEntityRes.getId());
            Collection<RoleEntity> roles = new HashSet<>();
            RoleEntity roleEntity = null;
            for (String role : user.getRoles()) {
                roleEntity = roleService.findRoleByCode(role);
                roleEntity = roleService.addNewUser(roleEntity.getId(),userEntityRes);
                roles.add(roleEntity);
            }
            userEntityRes.setRoles(roles);
            return userJPARepository.saveAndFlush(userEntityRes);
        }
        return null;
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userJPARepository.saveAndFlush(userEntity);
    }

    @Override
    public UserEntity findByUsername(String username) {
        Optional<UserEntity> user = userJPARepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public UserEntity findUserById(Long id) {
        Optional<UserEntity> user = userJPARepository.findById(id);
        return user.orElse(null);
    }

    //Tìm kiếm User theo tên
    @Override
    public UserEntity findByUserNameResponse(String username) {

        return findByUsername(username);

    }


    @Override
    public UserEntity findUserByIdResponse(Long id) {
        return findUserById(id);
    }

    @Override
    public UserEntity getUserToUpdate(Long id) {
        return userJPARepository.getOne(id);
    }


    //Sửa dổi User
    @Override
    public UserEntity updateUser(UpdateUserReq user) {

        UserEntity userEntity = userJPARepository.getOne(user.getId());

        String oldPassword = userEntity.getPassword();

        if (new BCryptPasswordEncoder().matches(user.getOldPass(), oldPassword) && userEntity != null) {


            userEntity.setPhone(user.getPhone());

            userEntity.setEmail(user.getEmail());

            userEntity.setFirstName(user.getFirstName());

            userEntity.setLastName(user.getLastName());


            userEntity.setPassword(new BCryptPasswordEncoder().encode(user.getNewPass()));

            userJPARepository.save(userEntity);

            return userEntity;
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
    public List<UserEntity> findByLastName(String lastName) {

        Streamable<UserEntity> user = userJPARepository.findByLastNameContaining(lastName);

        return user.toList();
    }

    @Override
    public ListResponsePagination findUsersByRoleCodePagination(String code, int page, int size) {

        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<UserEntity> users = userJPARepository.findByRoles_Code(code, pageable);


        return commonUtil.getListResponsePagination(users);

    }






    @Override
    public ListResponsePagination findByLastNameOrFirstNameContaining(String keyword, int page, int size) {

        Sort sort = Sort.by("lastName").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<UserEntity> users = userJPARepository.findByFirstNameOrLastNameContaining(keyword,keyword, pageable);


        return  commonUtil.getListResponsePagination(users);
    }

    @Override
    public void likeProduct(Long userId, Long productId) {
        UserEntity userEntity = getUserToUpdate(userId);
        ProductEntity productEntity = productService.getProductToUpdate(productId);
        if(userEntity.getLikeProductEntities() == null){
            List<ProductEntity> productEntities = new ArrayList<>();
            productEntities.add(productEntity);
            userEntity.setLikeProductEntities(productEntities);
        }else{
            userEntity.getLikeProductEntities().add(productEntity);
        }

        if(productEntity.getProductLikeByUserEntities() == null){
            List<UserEntity> userEntities = new ArrayList<>();
            userEntities.add(userEntity);
            productEntity.setProductLikeByUserEntities(userEntities);
        }else{
            productEntity.getProductLikeByUserEntities().add(userEntity);
        }

        productService.createNewOrUpdateProduct(productEntity);
        userJPARepository.saveAndFlush(userEntity);
    }

    @Override
    public void unLikeProduct(Long userId, Long proId) {
        UserEntity userEntity = getUserToUpdate(userId);
        ProductEntity productEntity = productService.getProductToUpdate(proId);

        if(userEntity.getLikeProductEntities() == null){
            return;
        }else{
            userEntity.getLikeProductEntities().remove(productEntity);
            productEntity.getProductLikeByUserEntities().remove(userEntity);
        }

        productService.createNewOrUpdateProduct(productEntity);
        updateUser(userEntity);
    }

    @Override
    public UserEntity addNewOrder(Long userId, OrderEntity orderEntity) {
        UserEntity userEntity = getUserToUpdate(userId);
        if(userEntity != null){
            if(userEntity.getOrders() == null){
                Collection<OrderEntity> orderEntities = new ArrayList<>();
                orderEntities.add(orderEntity);
                userEntity.setOrders(orderEntities);
            }else{
                userEntity.getOrders().add(orderEntity);
            }
            return userJPARepository.saveAndFlush(userEntity);
        }
        return null;
    }

    @Override
    public UserEntity addNewComment(Long userId, Long productId, String content) {
        UserEntity userEntity = getUserToUpdate(userId);
        ProductEntity productEntity = productService.getProductToUpdate(productId);

        ProductCommentEntity productCommentEntity = new ProductCommentEntity();
        productCommentEntity.setCommentKey(new CommentKey(productId, userId));
        productCommentEntity.setUserEntity(userEntity);
        productCommentEntity.setProductEntity(productEntity);
        productCommentEntity.setCommentContent(content);

        productCommentEntity = productCommentService.createNewComment(productCommentEntity);

//        checkCommentUser(userEntity, productCommentEntity);
//        productService.addNewCommentToProduct(productEntity, productCommentEntity);
        return userEntity;
    }

    @Override
    public ListResponsePagination findAllUsers(int page, int size) {
        Sort sort = Sort.by("lastName").descending();
        Pageable pageable = PageRequest.of(page - 1,size,sort);
        Page<UserEntity> userEntityList = userJPARepository.findAll(pageable);

        return commonUtil.getListResponsePagination(userEntityList);

    }


    public void checkCommentUser(UserEntity userEntity, ProductCommentEntity productCommentEntity){

        Collection<ProductCommentEntity> productCommentEntities1 = userEntity.getComments();
        if(userEntity.getComments() == null){
            Collection<ProductCommentEntity> productCommentEntities = new ArrayList<>();
            productCommentEntities.add(productCommentEntity);
            userEntity.setComments(productCommentEntities);
        }else{
            userEntity.getComments().add(productCommentEntity);
        }
        updateUser(userEntity);
    }
}
