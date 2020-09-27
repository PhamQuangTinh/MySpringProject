package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.UserEntity;
import ou.phamquangtinh.entity.middle_entity.ProductCommentDetailEntity;
import ou.phamquangtinh.entity.middle_entity.UserCommentEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentDetailKey;
import ou.phamquangtinh.repository.UserCommentJPARepository;
import ou.phamquangtinh.service.component_service.*;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserCommentService implements IUserCommentService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICommentDetailService commentDetailService;

    @Autowired
    private UserCommentJPARepository userCommentJPARepository;

    @Override
    public UserCommentEntity addNewUserToComment(Long userId) {
        UserEntity userEntity = userService.findUserById(userId);
        UserCommentEntity userCommentEntity = new UserCommentEntity();
        userCommentEntity.setUserEntity(userEntity);
        return createOrUpdateUserComment(userCommentEntity);
    }

    @Override
    public UserCommentEntity addNewCommentDetail(Long userCommentId, ProductCommentDetailEntity productCommentDetailEntity) {
        UserCommentEntity userCommentEntity = getUserToUpdate(userCommentId);
        if(userCommentEntity.getProductCommentDetailEntities() == null){
            Collection<ProductCommentDetailEntity> productCommentDetailEntities = new ArrayList<>();
            productCommentDetailEntities.add(productCommentDetailEntity);
            userCommentEntity.setProductCommentDetailEntities(productCommentDetailEntities);
        }else{
            userCommentEntity.getProductCommentDetailEntities().add(productCommentDetailEntity);
        }
        return createOrUpdateUserComment(userCommentEntity);

    }

    @Override
    public UserCommentEntity findUserCommentById(Long id) {
        return userCommentJPARepository.findById(id).orElse(null);
    }

    @Override
    public UserCommentEntity getUserToUpdate(Long id) {
        return userCommentJPARepository.getOne(id);
    }

    @Override
    public UserCommentEntity createOrUpdateUserComment(UserCommentEntity userCommentEntity) {
        return userCommentJPARepository.saveAndFlush(userCommentEntity);
    }

    @Override
    public UserCommentEntity createNewCommentToProduct(Long userId, Long proId, String commentContent) {

        UserEntity userEntity = userService.getUserToUpdate(userId);
        ProductEntity productEntity = productService.getProductToUpdate(proId);
        UserCommentEntity userCommentEntity = new UserCommentEntity();

        userCommentEntity.setUserEntity(userEntity);
        userCommentEntity = createOrUpdateUserComment(userCommentEntity);

        if(userCommentEntity != null && userEntity != null && productEntity != null){
            ProductCommentDetailEntity productCommentDetailEntity = new ProductCommentDetailEntity();
            productCommentDetailEntity.setCommentContent(commentContent);
            productCommentDetailEntity.setProductEntity(productEntity);
            productCommentDetailEntity.setCommentDetailKey(new CommentDetailKey(proId, userCommentEntity.getId()));
            productCommentDetailEntity.setUserCommentEntity(userCommentEntity);

            productCommentDetailEntity = commentDetailService.createOrUpdateCommentDetail(productCommentDetailEntity);


            return userCommentEntity;
        }

        return null;


    }


}
