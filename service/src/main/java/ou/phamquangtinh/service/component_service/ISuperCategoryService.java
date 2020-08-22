package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;

public interface ISuperCategoryService {

    SuperCategoryEntity createNewOrUpdateSuperCategory(SuperCategoryEntity superCategoryEntity);

    SuperCategoryEntity findSuperCategoryByName(String name);

    SuperCategoryEntity getToUpdateSupercategory(Long id);

    SuperCategoryEntity addNewCategory(Long superCategoryId, CategoryEntity categoryEntity);
}
