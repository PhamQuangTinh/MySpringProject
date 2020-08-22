package ou.phamquangtinh.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.entity.SuperCategoryEntity;
import ou.phamquangtinh.repository.SubCategoryJPARepository;
import ou.phamquangtinh.service.component_service.IAvailableProductService;
import ou.phamquangtinh.service.component_service.ISubCategoryService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class SubCategoryService implements ISubCategoryService {

    @Autowired
    private SubCategoryJPARepository subCategoryJPARepository;

    @Override
    public SubCategoryEntity createNewOrUpdateSubCategory(SubCategoryEntity subCategoryEntity) {
        return subCategoryJPARepository.saveAndFlush(subCategoryEntity);
    }

    @Override
    public SubCategoryEntity findSubCategoryByName(String name) {
        Optional<SubCategoryEntity> subCategoryEntity = subCategoryJPARepository.findByName(name);

        return subCategoryEntity.orElse(null);
    }

    @Override
    public SubCategoryEntity getSubCategoryToUpdate(Long id) {
        return subCategoryJPARepository.getOne(id);
    }

    @Override
    public SubCategoryEntity addNewProduct(Long subCategoryId, ProductEntity productEntity) {
        SubCategoryEntity subCategoryEntity = subCategoryJPARepository.getOne(subCategoryId);
        if(subCategoryEntity.getProductEntities() == null){
            Collection<ProductEntity> collection = new HashSet<>();
            collection.add(productEntity);
            subCategoryEntity.setProductEntities(collection);
        }else{
            Optional<SubCategoryEntity> findByProduct = subCategoryJPARepository.findByProductEntities_Id(productEntity.getId());
            if(!findByProduct.isPresent()){
                subCategoryEntity.getProductEntities().add(productEntity);
            }
        }
        SubCategoryEntity subCategoryEntityRes = subCategoryJPARepository.saveAndFlush(subCategoryEntity);
        System.out.println("Added: " + productEntity.getProductName() + " to " + subCategoryEntity.getName());
        return subCategoryEntityRes;
    }
}
