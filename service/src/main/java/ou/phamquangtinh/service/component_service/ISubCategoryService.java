package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.SubCategoryEntity;

public interface ISubCategoryService {

    SubCategoryEntity createNewSubCategoryEntity(SubCategoryEntity subCategoryEntity);

    SubCategoryEntity findSubCategoryByName(String name);
}
