package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.CategoryEntity;

public interface ICategoryService {

    CategoryEntity createNewCategory(CategoryEntity categoryEntity);

    CategoryEntity findCategoryByName(String name);
}
