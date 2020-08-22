package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.ProductAvatarEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;
import ou.phamquangtinh.repository.ProductJPARepository;
import ou.phamquangtinh.service.component_service.IProductService;

import java.util.*;

@Service

public class ProductService implements IProductService {

    @Autowired
    private ProductJPARepository productJPARepository;

    @Override
    public ProductEntity findProductByProductName(String productName) {
        return productJPARepository.findByProductName(productName).orElse(null);
    }

    @Override
    public ProductEntity createNewOrUpdateProduct(ProductEntity productEntity) {
        return productJPARepository.saveAndFlush(productEntity);
    }

    @Override
    public ProductEntity getProductToUpdate(Long id) {
        return productJPARepository.getOne(id);
    }

    @Override
    public ProductEntity addNewCategory(Long productId, CategoryEntity categoryEntity) {
        ProductEntity productEntity = productJPARepository.getOne(productId);

        if(productEntity.getCategoryEntities() == null){
            Collection<CategoryEntity> categoryEntityCollection = new HashSet<>();
            categoryEntityCollection.add(categoryEntity);
            productEntity.setCategoryEntities(categoryEntityCollection);
        }else{
            Optional<ProductEntity> findByCate = productJPARepository.findByCategoryEntities_Id(categoryEntity.getId());
            if(!findByCate.isPresent()){
                productEntity.getCategoryEntities().add(categoryEntity);
            }
        }
        return productJPARepository.saveAndFlush(productEntity);
    }

    @Override
    public ProductEntity addNewProductAvatar(Long productId, ProductAvatarEntity productAvatarEntity) {
        ProductEntity productEntity = productJPARepository.getOne(productId);

        if(productEntity.getProductAvatarEntities() == null){
            List<ProductAvatarEntity> productAvatarEntities = new ArrayList<>();
            productAvatarEntities.add(productAvatarEntity);
            productEntity.setProductAvatarEntities(productAvatarEntities);
        }else{
            productEntity.getProductAvatarEntities().add(productAvatarEntity);
        }
        ProductEntity proRes = productJPARepository.saveAndFlush(productEntity);

        return proRes;
    }

    @Override
    public ProductEntity addNewAvailableProduct(Long productId, AvailableProductsEntity availableProductsEntity) {
        ProductEntity productEntity = productJPARepository.getOne(productId);

        if(productEntity.getAvailableProductsEntities() == null){
            Collection<AvailableProductsEntity> availableProductsEntities = new HashSet<>();
            availableProductsEntities.add(availableProductsEntity);
            productEntity.setAvailableProductsEntities(availableProductsEntities);
        }else{
            productEntity.getAvailableProductsEntities().add(availableProductsEntity);
        }
        ProductEntity proRes = productJPARepository.saveAndFlush(productEntity);

        return proRes;
    }

    @Override
    public void addNewSubCategory(Long productId, SubCategoryEntity subCategoryEntity) {
        ProductEntity productEntity = productJPARepository.getOne(productId);
        if(productEntity.getSubCategoryEntity() == null){
            productEntity.setSubCategoryEntity(subCategoryEntity);
            productJPARepository.saveAndFlush(productEntity);
        }

    }
}
