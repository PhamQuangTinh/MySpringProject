package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.repository.ProductImagesJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.IProductImagesService;

@Service
public class ProductImagesService implements IProductImagesService {

    @Autowired
    private ProductImagesJPARepository productImagesJPARepository;

    public ProductImagesEntity saveProductImages(ProductImagesEntity productImagesEntity){
        return productImagesJPARepository.saveAndFlush(productImagesEntity);
    }
}
