package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.repository.SubCategoryJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.ISubCategoryService;

import java.util.Optional;

@Service
public class SubCategoryService implements ISubCategoryService {

    @Autowired
    private SubCategoryJPARepository subCategoryJPARepository;

    @Override
    public SubCategoryEntity createNewSubCategoryEntity(SubCategoryEntity subCategoryEntity) {
        return subCategoryJPARepository.saveAndFlush(subCategoryEntity);
    }

    @Override
    public SubCategoryEntity findSubCategoryByName(String name) {
        Optional<SubCategoryEntity> subCategoryEntity = subCategoryJPARepository.findByName(name);

        return subCategoryEntity.orElse(null);
    }
}
