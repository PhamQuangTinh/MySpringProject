package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.repository.ProductImagesJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.IProductImagesService;

import java.util.Set;

@Service
public class ProductImagesService implements IProductImagesService {

    @Autowired
    private ProductImagesJPARepository productImagesJPARepository;

    @Override
    public ProductImagesEntity findProductImagesByImageLink(String imageLink) {
        return productImagesJPARepository.findByImageLink(imageLink).orElse(null);
    }

    @Override
    public ProductImagesEntity createNewOrUpdateProductImages(ProductImagesEntity productImagesEntity) {
        return productImagesJPARepository.saveAndFlush(productImagesEntity);
    }

    @Override
    public Set<ProductImagesEntity> findProductImagesByProductId(Long proId) {
        return productImagesJPARepository.findByProductColorEntity_Id_ProductId(proId);
    }

    @Override
    public Set<ProductImagesEntity> findProductImagesByProductIdAndColorId(Long proId, Long colorId) {
        return productImagesJPARepository.findByProductColorEntity_Id_ProductIdAndProductColorEntity_Id_ColorId(proId, colorId);
    }

}
