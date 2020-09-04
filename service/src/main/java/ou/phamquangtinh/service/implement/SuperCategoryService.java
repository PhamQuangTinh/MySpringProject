package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;
import ou.phamquangtinh.repository.SuperCategoryJPARepository;
import ou.phamquangtinh.service.component_service.ISuperCategoryService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class SuperCategoryService implements ISuperCategoryService {

    @Autowired
    private SuperCategoryJPARepository superCategoryJPARepository;

    @Override
    public SuperCategoryEntity createNewOrUpdateSuperCategory(SuperCategoryEntity superCategoryEntity) {
          return superCategoryJPARepository.saveAndFlush(superCategoryEntity);
    }

    @Override
    public SuperCategoryEntity findSuperCategoryByName(String name) {
        Optional<SuperCategoryEntity> superCategoryEntity = superCategoryJPARepository.findByName(name);

        return superCategoryEntity.orElse(null);
    }

    @Override
    public SuperCategoryEntity getToUpdateSupercategory(Long id) {
        return superCategoryJPARepository.getOne(id);
    }

    @Override
    public SuperCategoryEntity addNewCategory(Long superCategoryId, CategoryEntity categoryEntity) {
        SuperCategoryEntity superCategoryEntity = superCategoryJPARepository.getOne(superCategoryId);
        if(superCategoryEntity.getCategoryEntities() == null){
            Collection<CategoryEntity> collection = new HashSet<>();
            collection.add(categoryEntity);
            superCategoryEntity.setCategoryEntities(collection);
        }else{
            Optional<SuperCategoryEntity> findByCategory = superCategoryJPARepository.findByCategoryEntities_Id(categoryEntity.getId());
            if(!findByCategory.isPresent()){
                superCategoryEntity.getCategoryEntities().add(categoryEntity);
            }
        }
//        System.out.println("Added: " + categoryEntity.getCategoryName() + " to " + superCategoryEntity.getName());
        return superCategoryJPARepository.saveAndFlush(superCategoryEntity);
    }
}
