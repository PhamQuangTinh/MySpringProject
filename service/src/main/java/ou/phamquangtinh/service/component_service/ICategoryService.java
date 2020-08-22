package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;

public interface ICategoryService {

    CategoryEntity createNewOrUpdateCategory(CategoryEntity categoryEntity);

    CategoryEntity findCategoryByName(String name);

    CategoryEntity addNewSubCategory(Long categoryId, SubCategoryEntity subCategoryEntity);

    CategoryEntity addNewProduct(Long categoryId, ProductEntity productEntity);

    CategoryEntity getCategoryToUpdate(Long id);

}
