package ou.phamquangtinh.service.component_service;

import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;

public interface ISuperCategoryService {

    SuperCategoryEntity createNewSuperCategory(SuperCategoryEntity superCategoryEntity);

    SuperCategoryEntity findSuperCategoryByName(String name);

}
