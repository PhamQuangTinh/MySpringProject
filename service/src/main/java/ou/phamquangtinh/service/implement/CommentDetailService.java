package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.middle_entity.ProductCommentDetailEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.CommentDetailKey;
import ou.phamquangtinh.repository.CommentDetailJPARepository;
import ou.phamquangtinh.service.component_service.ICommentDetailService;

import java.util.List;

@Service
public class CommentDetailService implements ICommentDetailService {

    @Autowired
    private CommentDetailJPARepository commentDetailJPARepository;

    @Override
    public ProductCommentDetailEntity createOrUpdateCommentDetail(ProductCommentDetailEntity commentDetailEntity) {
        return commentDetailJPARepository.saveAndFlush(commentDetailEntity);
    }

    @Override
    public ProductCommentDetailEntity getCommentDetailToUpdate(CommentDetailKey id) {
        return commentDetailJPARepository.getOne(id);
    }

    @Override
    public List<ProductCommentDetailEntity> findAllCommentsProduct(int page, Long proId) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<ProductCommentDetailEntity> entities = commentDetailJPARepository.findByCommentDetailKey_ProductId(proId, pageable);
        return entities.getContent();
    }
}
