package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.ProductAvatarEntity;
import ou.phamquangtinh.repository.ProductAvatarJPARepository;
import ou.phamquangtinh.service.component_service.IProductAvatarService;

@Service
public class ProductAvatarService implements IProductAvatarService {

    @Autowired
    private ProductAvatarJPARepository productAvatarJPARepository;

    @Override
    public ProductAvatarEntity createNewProductAvatar(ProductAvatarEntity productAvatarEntity) {
        return productAvatarJPARepository.saveAndFlush(productAvatarEntity);
    }
}
