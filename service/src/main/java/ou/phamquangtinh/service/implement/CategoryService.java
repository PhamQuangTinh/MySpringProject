package ou.phamquangtinh.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phamquangtinh.entity.CategoryEntity;
import ou.phamquangtinh.entity.ProductEntity;
import ou.phamquangtinh.entity.SubCategoryEntity;
import ou.phamquangtinh.repository.CategoryJPARepository;
import ou.phamquangtinh.service.component_service.ICategoryService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryJPARepository categoryJPARepository;
    @Override
    public CategoryEntity createNewOrUpdateCategory(CategoryEntity categoryEntity) {
        return categoryJPARepository.saveAndFlush(categoryEntity);
    }

    @Override
    public CategoryEntity findCategoryByName(String name) {
        Optional<CategoryEntity> categoryEntity = categoryJPARepository.findByCategoryName(name);
        return categoryEntity.orElse(null);
    }

    @Override
    public CategoryEntity addNewSubCategory(Long categoryId, SubCategoryEntity subCategoryEntity) {
        CategoryEntity categoryEntity = categoryJPARepository.getOne(categoryId);
        if(categoryEntity.getSubCategoryEntities() == null){
            Collection<SubCategoryEntity> subCategoryEntities = new HashSet<>();
            subCategoryEntities.add(subCategoryEntity);
            categoryEntity.setSubCategoryEntities(subCategoryEntities);
        }else{
            Optional<CategoryEntity> findBySubCategory = categoryJPARepository.findBySubCategoryEntities_Id(subCategoryEntity.getId());
            if(!findBySubCategory.isPresent()){
                categoryEntity.getSubCategoryEntities().add(subCategoryEntity);
            }
        }
        System.out.println("Added: " + subCategoryEntity.getName() + " to " + categoryEntity.getCategoryName());
        return categoryJPARepository.saveAndFlush(categoryEntity);
    }

    @Override
    public CategoryEntity addNewProduct(Long categoryId, ProductEntity productEntity) {
        CategoryEntity categoryEntity = categoryJPARepository.getOne(categoryId);
        if(categoryEntity.getProductEntities() == null){
            Collection<ProductEntity> productEntities = new HashSet<>();
            productEntities.add(productEntity);
            categoryEntity.setProductEntities(productEntities);
        }else{
            Optional<CategoryEntity> findByProduct = categoryJPARepository.findByProductEntities_Id(productEntity.getId());
            if(!findByProduct.isPresent()){
                categoryEntity.getProductEntities().add(productEntity);
            }
        }
        System.out.println("Added: " + productEntity.getProductName() + " to " + categoryEntity.getCategoryName());
        return categoryJPARepository.saveAndFlush(categoryEntity);
    }

    @Override
    public CategoryEntity getCategoryToUpdate(Long id) {
        return categoryJPARepository.getOne(id);
    }

}
