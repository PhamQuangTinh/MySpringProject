package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.ProductAvatarEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.entity.middle_entity.AvailableProductsEntity;
import ou.phamquangtinh.service.implement.ProductService;

import java.util.Optional;

public interface IProductService {

    ProductEntity findProductByProductName(String productName);

    ProductEntity createNewOrUpdateProduct(ProductEntity productEntity);

    ProductEntity getProductToUpdate(Long id);

    ProductEntity addNewCategory(Long productId, CategoryEntity categoryEntity);

    ProductEntity addNewProductAvatar(Long productId, ProductAvatarEntity productAvatarEntity);

    ProductEntity addNewAvailableProduct(Long productId, AvailableProductsEntity availableProductsEntity);

    void addNewSubCategory(Long productId, SubCategoryEntity subCategoryEntity);

}
