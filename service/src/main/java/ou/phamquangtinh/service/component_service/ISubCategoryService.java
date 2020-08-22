package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;

public interface ISubCategoryService {

    SubCategoryEntity createNewOrUpdateSubCategory(SubCategoryEntity subCategoryEntity);

    SubCategoryEntity findSubCategoryByName(String name);

    SubCategoryEntity getSubCategoryToUpdate(Long id);

    SubCategoryEntity addNewProduct(Long subCategoryId, ProductEntity productEntity);
}
