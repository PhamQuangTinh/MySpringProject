package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.ProductImagesEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductEntity;
import ou.phamquangtinh.entity.middle_entity.ProductColorEntity;
import ou.phamquangtinh.entity.middle_entity.embaddableEntity.ProductColorKey;
import ou.phamquangtinh.repository.ProductColorJPARepository;
import ou.phamquangtinh.service.component_service.IProductColorService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class ProductColorService implements IProductColorService {

    @Autowired
    private ProductColorJPARepository productColorJPARepository;

    @Override
    public ProductColorEntity findProductColorById(ProductColorKey id) {
        return productColorJPARepository.getOne(id);
    }

    @Override
    public List<ProductColorEntity> findProductColorByProductId(Long id) {
        List<ProductColorEntity> productColorEntity = productColorJPARepository.findById_ProductId(id);
        return  productColorEntity;
    }

    @Override
    public ProductColorEntity createOrUpdateProductColor(ProductColorEntity productColorEntity) {
        return productColorJPARepository.saveAndFlush(productColorEntity);

    }

    @Override
    public ProductColorEntity addNewAvailableProduct(ProductColorKey id, AvailableProductEntity availableProductEntity) {
        ProductColorEntity productColorEntity = productColorJPARepository.getOne(id);
        if(productColorEntity.getAvailableProductEntities() == null){
            Collection<AvailableProductEntity> availableProductEntities = new HashSet<>();
            availableProductEntities.add(availableProductEntity);
            productColorEntity.setAvailableProductEntities(availableProductEntities);
        }else{
            productColorEntity.getAvailableProductEntities().add(availableProductEntity);
        }

        return productColorJPARepository.saveAndFlush(productColorEntity);
    }

    @Override
    public ProductColorEntity addNewProductImages(ProductColorKey id, ProductImagesEntity productImagesEntity) {
        ProductColorEntity productColorEntity = productColorJPARepository.getOne(id);
        if(productColorEntity.getProductImagesEntities() == null){
            Collection<ProductImagesEntity> productImagesEntities = new HashSet<>();
            productImagesEntities.add(productImagesEntity);
            productColorEntity.setProductImagesEntities(productImagesEntities);
        }else{
            productColorEntity.getProductImagesEntities().add(productImagesEntity);
        }

        return productColorJPARepository.saveAndFlush(productColorEntity);
    }
}
