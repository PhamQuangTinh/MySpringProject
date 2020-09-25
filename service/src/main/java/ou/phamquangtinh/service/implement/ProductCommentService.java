package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.dto.response.ListResponsePagination;
import ou.phamquangtinh.entity.middle_entity.ProductCommentEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentKey;
import ou.phamquangtinh.repository.ProductCommentJPARepository;
import ou.phamquangtinh.service.component_service.IProductCommentService;
import ou.phamquangtinh.service.util.CommonUtil;

@Service
public class ProductCommentService implements IProductCommentService {

    @Autowired
    private ProductCommentJPARepository productCommentJPARepository;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public ProductCommentEntity createNewComment(ProductCommentEntity productCommentEntity) {
        return productCommentJPARepository.saveAndFlush(productCommentEntity);
//        if(findProductByCommentKey(productCommentEntity.getCommentKey()) == null){
//            return productCommentJPARepository.saveAndFlush(productCommentEntity);
//        }else{
//            ProductCommentEntity productComment = new ProductCommentEntity();
//            productComment.setCommentKey(productCommentEntity.getCommentKey());
//            productComment.setUserEntity(productCommentEntity.getUserEntity());
//            productComment.setProductEntity(productCommentEntity.getProductEntity());
//            productComment.setCommentContent(productCommentEntity.getCommentContent());
//            return productCommentJPARepository.saveAndFlush(productComment);
//        }

    }

    @Override
    public ProductCommentEntity findProductByCommentKey(CommentKey commentKey) {
        return productCommentJPARepository.findByCommentKey(commentKey).orElse(null);
    }

    @Override
    public ListResponsePagination findCommentsProduct(Long proId, int page) {
        Sort sort = Sort.by("CreatedDate");
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        Page<ProductCommentEntity> res = productCommentJPARepository.findByCommentKey_ProductId(proId,pageable);

        return commonUtil.getListResponsePagination(res);
    }
}