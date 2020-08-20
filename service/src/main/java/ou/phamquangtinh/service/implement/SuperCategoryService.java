package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.SuperCategoryEntity;
import ou.phamquangtinh.repository.SuperCategoryJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.ISuperCategoryService;

import java.util.Optional;

@Service
public class SuperCategoryService implements ISuperCategoryService {

    @Autowired
    private SuperCategoryJPARepository superCategoryJPARepository;

    @Override
    public SuperCategoryEntity createNewSuperCategory(SuperCategoryEntity superCategoryEntity) {
        return superCategoryJPARepository.saveAndFlush(superCategoryEntity);
    }

    @Override
    public SuperCategoryEntity findSuperCategoryByName(String name) {
        Optional<SuperCategoryEntity> superCategoryEntity = superCategoryJPARepository.findByName(name);

        return superCategoryEntity.orElse(null);
    }
}
